package com.management.employee.service;

import com.management.employee.model.LeaveInfo;
import com.management.employee.repository.LeaveDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LeaveInfoService {
    @Autowired
    private LeaveDetailsRepository leaveDetailsRepository;

    public LeaveInfo getLeaveByUserId(int userId) {
        return leaveDetailsRepository.findByUserId(userId);
    }

    public LeaveInfo addUpdateLeave(LeaveInfo leaveInfo){
        return leaveDetailsRepository.save(leaveInfo);
    }
}
