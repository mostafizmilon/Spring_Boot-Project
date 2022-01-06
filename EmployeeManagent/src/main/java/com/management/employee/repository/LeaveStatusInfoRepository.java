package com.management.employee.repository;

import com.management.employee.enums.LeaveStatus;
import com.management.employee.model.LeaveStatusInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface LeaveStatusInfoRepository extends JpaRepository<LeaveStatusInfo, Integer> {
    List<LeaveStatusInfo> findByUserId(int userId);

    List<LeaveStatusInfo> findAllByOrderByStatus();

    List<LeaveStatusInfo> findByUserIdAndStatus(int userId, LeaveStatus status);
}
