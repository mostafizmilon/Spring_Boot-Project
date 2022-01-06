package com.management.employee.repository;

import com.management.employee.model.EmployeeSalaryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmployeeSalaryInfoRepository extends JpaRepository<EmployeeSalaryInfo, Integer> {
    EmployeeSalaryInfo findByUserIdAndMonthAndYear(Integer userId, int month, int year);

    List<EmployeeSalaryInfo> findByUserId(Integer userId);
}
