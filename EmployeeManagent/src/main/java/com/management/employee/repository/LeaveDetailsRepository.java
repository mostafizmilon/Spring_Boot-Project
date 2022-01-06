package com.management.employee.repository;

import com.management.employee.model.LeaveInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LeaveDetailsRepository extends JpaRepository<LeaveInfo, Integer> {
    LeaveInfo findByUserId(int userId);
}
