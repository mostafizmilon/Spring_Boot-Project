package com.management.employee.controller;


import com.management.employee.dto.EmployeeSalaryInfoDto;
import com.management.employee.enums.LeaveStatus;
import com.management.employee.model.EmployeeSalaryInfo;
import com.management.employee.model.LeaveInfo;
import com.management.employee.model.LeaveStatusInfo;
import com.management.employee.model.SalaryInfo;
import com.management.employee.model.UserInfo;
import com.management.employee.service.EmployeeSalaryInfoService;
import com.management.employee.service.LeaveInfoService;
import com.management.employee.service.LeaveStatusInfoService;
import com.management.employee.service.SalaryInfoService;
import com.management.employee.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
public class UserController {
	@Autowired
	private UserInfoService userService;
	@Autowired
	private SalaryInfoService salaryInfoService;
	@Autowired
	private LeaveInfoService leaveInfoService;
	@Autowired
	private LeaveStatusInfoService leaveStatusInfoService;
	@Autowired
	private EmployeeSalaryInfoService employeeSalaryInfoService;

	@GetMapping("/user")
	public String me(Model model, Principal principal) {
		UserInfo userInfo = userService.getUserInfoByEmail(principal.getName());
		SalaryInfo salaryInfo = salaryInfoService.getSalaryByUserId(userInfo.getId());
		LeaveInfo leaveInfo = leaveInfoService.getLeaveByUserId(userInfo.getId());

		model.addAttribute("user", userInfo);
		model.addAttribute("salary", salaryInfo);
		model.addAttribute("leave", leaveInfo);

		return "userdetails";
	}

	@GetMapping("/user/leave")
	public String myLeave(Model model, Principal principal) {
		UserInfo userInfo = userService.getUserInfoByEmail(principal.getName());

		model.addAttribute("user", userInfo);
		model.addAttribute("leave", leaveInfoService.getLeaveByUserId(userInfo.getId()));
		model.addAttribute("newLeave", new LeaveStatusInfo());
		model.addAttribute("history", leaveStatusInfoService.getLeaveStatusInfoByUserId(userInfo.getId()));

		return "leavedetails";
	}

	@PostMapping("/user/leave")
	public String applyLeave(@Valid @ModelAttribute("newLeave") LeaveStatusInfo leaveStatusInfo, BindingResult bindingResult,
							 Model model, Principal principal) {
		UserInfo userInfo = userService.getUserInfoByEmail(principal.getName());
		model.addAttribute("user", userInfo);
		model.addAttribute("leave", leaveInfoService.getLeaveByUserId(userInfo.getId()));
		model.addAttribute("history", leaveStatusInfoService.getLeaveStatusInfoByUserId(userInfo.getId()));

		long diffInMillies = Math.abs(leaveStatusInfo.getLeaveTo().getTime() - leaveStatusInfo.getLeaveFrom().getTime());
		int diff = (int) TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

		if(diff < 0 || leaveStatusInfo.getLeaveFrom().after(leaveStatusInfo.getLeaveTo())){
			bindingResult.rejectValue("leaveFrom", "leaveFrom.required", "invalid date range");
		}
		if(bindingResult.hasErrors()){
			return "leavedetails";
		}

		leaveStatusInfo.setStatus(LeaveStatus.PENDING);
		leaveStatusInfo.setDays(diff+1);
		leaveStatusInfo.setUserId(userInfo.getId());

		leaveStatusInfoService.addUpdateLeaveStatusInfo(leaveStatusInfo);

		return "redirect:/user/leave";
	}

	@GetMapping("/user/salary")
	public String mySalary(Model model, Principal principal) {
		UserInfo userInfo = userService.getUserInfoByEmail(principal.getName());
		List<EmployeeSalaryInfoDto> dtos = new ArrayList<>();

		for(EmployeeSalaryInfo employeeSalaryInfo: employeeSalaryInfoService.getByUserId(userInfo.getId())){
			EmployeeSalaryInfoDto dto = new EmployeeSalaryInfoDto();
			BeanUtils.copyProperties(employeeSalaryInfo, dto);
			dto.setMonthName(getMonthName(dto.getMonth()));
			dto.setUserName(userInfo.getFull_name());
			dtos.add(dto);
		}

		model.addAttribute("salaries", dtos);
		return "salarydetails";
	}

	private String getMonthName(int month){
		switch (month){
			case 0: return "January";
			case 1: return "February";
			case 2: return "March";
			case 3: return "April";
			case 4: return "May";
			case 5: return "June";
			case 6: return "July";
			case 7: return "August";
			case 8: return "September";
			case 9: return "October";
			case 10: return "November";
			default: return "December";

		}
	}
}
