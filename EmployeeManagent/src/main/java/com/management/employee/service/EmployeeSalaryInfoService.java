package com.management.employee.service;

import com.management.employee.model.EmployeeSalaryInfo;
import com.management.employee.repository.EmployeeSalaryInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeSalaryInfoService {

    @Autowired
    private EmployeeSalaryInfoRepository employeeSalaryInfoRepository;

    public EmployeeSalaryInfo getByUserIdAndMonthAndYear(Integer userId, int month, int year){
        return employeeSalaryInfoRepository.findByUserIdAndMonthAndYear(userId, month, year);
    }

    public void deleteEmployeeSalaryInfo(EmployeeSalaryInfo salaryInfo){
        employeeSalaryInfoRepository.delete(salaryInfo);
    }

    public EmployeeSalaryInfo addUpdateEmployeeSalaryInfo(EmployeeSalaryInfo salaryInfo){
        return employeeSalaryInfoRepository.save(salaryInfo);
    }

    public List<EmployeeSalaryInfo> getByUserId(Integer userId){
        return employeeSalaryInfoRepository.findByUserId(userId);
    }
}
