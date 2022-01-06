package com.management.employee.repository;

import com.management.employee.model.SalaryStructureInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryStructureInfoRepository extends JpaRepository<SalaryStructureInfo, Integer> {
}
