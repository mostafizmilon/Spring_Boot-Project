package com.management.employee.repository;

import com.management.employee.model.SalaryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface SalaryDetailsRepository extends JpaRepository<SalaryInfo, Integer> {
    SalaryInfo findByUserId(int userId);
}
