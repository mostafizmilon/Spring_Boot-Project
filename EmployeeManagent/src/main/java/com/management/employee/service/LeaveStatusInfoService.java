package com.management.employee.service;

import com.management.employee.enums.LeaveStatus;
import com.management.employee.model.LeaveStatusInfo;
import com.management.employee.repository.LeaveStatusInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LeaveStatusInfoService {
    @Autowired
    private LeaveStatusInfoRepository leaveStatusInfoRepository;

    public List<LeaveStatusInfo> getLeaveStatusInfoByUserId(int userId){
        return leaveStatusInfoRepository.findByUserId(userId);
    }

    public LeaveStatusInfo addUpdateLeaveStatusInfo(LeaveStatusInfo leaveStatusInfo){
        return leaveStatusInfoRepository.save(leaveStatusInfo);
    }

    public List<LeaveStatusInfo> getAllLeaveInfo() {
        return leaveStatusInfoRepository.findAllByOrderByStatus();
    }

    public LeaveStatusInfo getLeaveStatusInfoById(int id) {
        return leaveStatusInfoRepository.findById(id).get();
    }

    public List<LeaveStatusInfo> getLeaveStatusInfoByUserIdAndStatus(int userId, LeaveStatus status){
        return leaveStatusInfoRepository.findByUserIdAndStatus(userId, status);
    }
}
