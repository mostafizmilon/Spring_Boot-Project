package com.management.employee.controller;

import com.management.employee.dto.LeaveStatusInfoDto;
import com.management.employee.dto.PaySalarayDto;
import com.management.employee.dto.UserDto;
import com.management.employee.enums.LeaveStatus;
import com.management.employee.enums.SalaryStatus;
import com.management.employee.model.EmployeeSalaryInfo;
import com.management.employee.model.LeaveInfo;
import com.management.employee.model.LeaveStatusInfo;
import com.management.employee.model.SalaryInfo;
import com.management.employee.model.SalaryStructureInfo;
import com.management.employee.model.UserInfo;
import com.management.employee.service.EmployeeSalaryInfoService;
import com.management.employee.service.LeaveInfoService;
import com.management.employee.service.LeaveStatusInfoService;
import com.management.employee.service.SalaryInfoService;
import com.management.employee.service.SalaryStructureInfoService;
import com.management.employee.service.UserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Controller
public class EmployeeController {
    @Autowired
    private UserInfoService userService;
    @Autowired
    private SalaryInfoService salaryInfoService;
    @Autowired
    private LeaveInfoService leaveInfoService;
    @Autowired
    private SalaryStructureInfoService salaryStructureInfoService;
    @Autowired
    private EmployeeSalaryInfoService employeeSalaryInfoService;

    @Autowired
    private LeaveStatusInfoService leaveStatusInfoService;

    @GetMapping("/employee")
    public String employees(Principal principal, Model model) {
        model.addAttribute("employees", userService.getAllUserInfo());
        return "employees";
    }

    @GetMapping("/employee/{id}")
    public String employee(@PathVariable int id, Principal principal, Model model) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userService.getUserInfoById(id), userDto);
        if(userDto.isEnabled()){
            userDto.setLeaves(leaveInfoService.getLeaveByUserId(id).getLeaves());
            userDto.setSalary(salaryInfoService.getSalaryByUserId(id).getSalary());
        }

        model.addAttribute("employee", userDto);
        return "employee";
    }

    @PostMapping("/employee/{id}")
    public String employeeUpdate(@PathVariable int id, @Valid @ModelAttribute("employee") UserDto userDto, BindingResult bindingResult,
                                 Principal principal, Model model) {
        if(bindingResult.hasErrors()){
            userDto.setId(id);
            return "employee";
        }

        SalaryInfo salaryInfo = new SalaryInfo();
        LeaveInfo leaveInfo= new LeaveInfo();

        UserInfo userInfo = userService.getUserInfoById(id);
        if(! userInfo.isEnabled()) {
            userInfo.setEnabled(true);

            salaryInfo.setSalary(userDto.getSalary());
            salaryInfo.setUserId(id);

            leaveInfo.setLeaves(userDto.leaves);
            leaveInfo.setUserId(id);
            leaveInfo.setRemainingLeaves(userDto.leaves);
        } else {
            salaryInfo = salaryInfoService.getSalaryByUserId(id);
            salaryInfo.setSalary(userDto.getSalary());

            leaveInfo = leaveInfoService.getLeaveByUserId(id);
            leaveInfo.setLeaves(userDto.leaves);
        }

        leaveInfoService.addUpdateLeave(leaveInfo);
        salaryInfoService.addUpdateSalary(salaryInfo);

        userInfo.setDesignation(userDto.getDesignation());
        userService.updateUser(id, userInfo);

        return "redirect:/employee";
    }

    @GetMapping("/employee/leave")
    public String employeeLeave(Principal principal, Model model) {
        List<LeaveStatusInfo> leaveStatusInfos = leaveStatusInfoService.getAllLeaveInfo();
        List<LeaveStatusInfoDto> dto = leaveStatusInfos.stream().map(leaveStatusInfo -> {
            LeaveStatusInfoDto leaveStatusInfoDto = new LeaveStatusInfoDto();
            BeanUtils.copyProperties(leaveStatusInfo, leaveStatusInfoDto);
            leaveStatusInfoDto.setUserName(userService.getUserInfoById(leaveStatusInfo.getUserId()).getFull_name());
            return leaveStatusInfoDto;
        }).collect(Collectors.toList());

        model.addAttribute("applications", dto);
        return "employeeleaves";
    }

    @GetMapping("/employee/leave/{id}/{status}")
    public String updateEmployeeLeave(@PathVariable int id, @PathVariable LeaveStatus status,
                                      Principal principal, Model model) {
        LeaveStatusInfo leaveStatusInfo = leaveStatusInfoService.getLeaveStatusInfoById(id);
        leaveStatusInfo.setStatus(status);
        leaveStatusInfoService.addUpdateLeaveStatusInfo(leaveStatusInfo);

        if(status.equals(LeaveStatus.ACCEPTED)){
            LeaveInfo leaveInfo = leaveInfoService.getLeaveByUserId(leaveStatusInfo.getUserId());
            leaveInfo.setRemainingLeaves(leaveInfo.getRemainingLeaves() - leaveStatusInfo.getDays());
            leaveInfoService.addUpdateLeave(leaveInfo);
        }

        return "redirect:/employee/leave";
    }

    @GetMapping("/employee/salary")
    public String employeeSalary(Principal principal, Model model) {
        List<PaySalarayDto> dtos = new ArrayList<>();
        for(UserInfo userInfo : userService.getAllUserInfo()){
            PaySalarayDto dto = new PaySalarayDto();
            dto.setUserId(userInfo.getId());
            dto.setUserName(userInfo.getFull_name());

            SalaryInfo salaryInfo = salaryInfoService.getSalaryByUserId(userInfo.getId());
            dto.setSalary(salaryInfo.getSalary());
            dto.setUnpaidLeaves(calculateUnpaidLeaves(userInfo.getId()));

            SalaryStructureInfo salaryStructureInfo = salaryStructureInfoService.getSalaryStructureInfo();

            dto.setBasic((float) ((dto.getSalary() * salaryStructureInfo.getBasic()) / 100));
            dto.setConveyance((float) ((dto.getSalary() * salaryStructureInfo.getConveyance()) / 100));
            dto.setHouseRent((float) ((dto.getSalary() * salaryStructureInfo.getHouseRent()) / 100));
            dto.setMedical((float) ((dto.getSalary() * salaryStructureInfo.getMedical()) / 100));

            if(dto.getUnpaidLeaves() > 0)
                dto.setDeduction((float) ((dto.getBasic() / 30) * dto.getUnpaidLeaves()));

            Calendar cal =  Calendar.getInstance();
            cal.add(Calendar.MONTH, -1);
            int salaryYear = cal.get(Calendar.YEAR);
            int salaryMonth = cal.get(Calendar.MONTH);
            if(employeeSalaryInfoService.getByUserIdAndMonthAndYear(userInfo.getId(), salaryMonth, salaryYear) !=null){
                dto.setStatus(SalaryStatus.PAID.name());
            }
            dto.setMonth(cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH ));
            dto.setSalary(dto.getSalary() - dto.getDeduction());

            dtos.add(dto);
        }

        model.addAttribute("salaries", dtos);
        return "employeesalaries";
    }

    @GetMapping("/employee/salary/{userId}/{status}")
    public String employeeSalaryStatus(@PathVariable Integer userId, @PathVariable String status, Principal principal) {
        Calendar cal =  Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        int salaryYear = cal.get(Calendar.YEAR);
        int salaryMonth = cal.get(Calendar.MONTH);

        EmployeeSalaryInfo dto = new EmployeeSalaryInfo();
        dto = employeeSalaryInfoService.getByUserIdAndMonthAndYear(userId, salaryMonth, salaryYear);
        if(dto != null){
            employeeSalaryInfoService.deleteEmployeeSalaryInfo(dto);
        } else {
            dto = new EmployeeSalaryInfo();
            SalaryStructureInfo salaryStructureInfo = salaryStructureInfoService.getSalaryStructureInfo();

            SalaryInfo salaryInfo = salaryInfoService.getSalaryByUserId(userId);
            dto.setSalary(salaryInfo.getSalary());
            dto.setUnpaidLeaves(calculateUnpaidLeaves(userId));

            dto.setBasic((float) ((dto.getSalary() * salaryStructureInfo.getBasic()) / 100));
            dto.setConveyance((float) ((dto.getSalary() * salaryStructureInfo.getConveyance()) / 100));
            dto.setHouseRent((float) ((dto.getSalary() * salaryStructureInfo.getHouseRent()) / 100));
            dto.setMedical((float) ((dto.getSalary() * salaryStructureInfo.getMedical()) / 100));

            if(dto.getUnpaidLeaves() > 0)
                dto.setDeduction((float) ((dto.getBasic() / 30) * dto.getUnpaidLeaves()));

            dto.setYear(salaryYear);
            dto.setMonth(salaryMonth);
            dto.setSalaryId(salaryInfo.getId());
            dto.setUserId(userId);
            
            dto.setSalary(dto.getSalary() - dto.getDeduction());

            employeeSalaryInfoService.addUpdateEmployeeSalaryInfo(dto);
        }

        return "redirect:/employee/salary";
    }

    public int calculateUnpaidLeaves(int userId){
        int uppaidDays = 0;
        List<LeaveStatusInfo> leaveStatusInfos = leaveStatusInfoService.getLeaveStatusInfoByUserIdAndStatus(userId, LeaveStatus.REJECTED);

        Calendar cal =  Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);

        //String salaryMonth = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH );
        int salaryYear = cal.get(Calendar.YEAR);
        int salaryMonth = cal.get(Calendar.MONTH);
        for (LeaveStatusInfo leaveStatusInfo: leaveStatusInfos){
            if(leaveStatusInfo.getLeaveFrom().getMonth() == salaryMonth &&
                leaveStatusInfo.getLeaveFrom().getYear() == salaryYear){
                uppaidDays += leaveStatusInfo.getDays();
            }
        }
        return uppaidDays;
    }
}
