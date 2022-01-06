package com.management.employee.dto;

import com.management.employee.enums.LeaveStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;


public class LeaveStatusInfoDto {
    private Integer id;

    @NotNull(message = "Leave from can not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leaveFrom = new Date();
    @NotNull(message = "Leave to can not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leaveTo = new Date();

    private int days;

    @Enumerated(EnumType.STRING)
    private LeaveStatus status;

    @NotBlank(message = "Title from can not be empty")
    private String title;

    private int userId;

    private String userName;

    public LeaveStatusInfoDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getLeaveFrom() {
        return leaveFrom;
    }

    public void setLeaveFrom(Date leaveFrom) {
        this.leaveFrom = leaveFrom;
    }

    public Date getLeaveTo() {
        return leaveTo;
    }

    public void setLeaveTo(Date leaveTo) {
        this.leaveTo = leaveTo;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
