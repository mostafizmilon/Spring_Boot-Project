package com.management.employee.service;

import com.management.employee.model.SalaryStructureInfo;
import com.management.employee.repository.SalaryStructureInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalaryStructureInfoService {
    @Autowired
    private SalaryStructureInfoRepository salaryStructureInfoRepository;

    public SalaryStructureInfo getSalaryStructureInfo(){
        return salaryStructureInfoRepository.findAll().get(0);
    }
}
