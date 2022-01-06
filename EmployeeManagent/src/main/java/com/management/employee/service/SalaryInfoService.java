package com.management.employee.service;

import com.management.employee.model.SalaryInfo;
import com.management.employee.repository.SalaryDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SalaryInfoService {
    @Autowired
    private SalaryDetailsRepository salaryDetailsRepository;

    public SalaryInfo getSalaryByUserId(int userId) {
        return salaryDetailsRepository.findByUserId(userId);
    }

    public SalaryInfo addUpdateSalary(SalaryInfo salaryInfo) {
        return salaryDetailsRepository.save(salaryInfo);
    }
}
