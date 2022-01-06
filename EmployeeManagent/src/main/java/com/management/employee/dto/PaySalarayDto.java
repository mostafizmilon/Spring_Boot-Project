package com.management.employee.dto;

import com.management.employee.enums.SalaryStatus;

public class PaySalarayDto {
    private String userName;
    private int userId;
    private double salary;
    private int unpaidLeaves = 0;
    private float deduction = 0;

    private float basic;
    private float houseRent;
    private float medical;
    private float conveyance;

    private String status = SalaryStatus.UNPAID.name();

    private String month;

    public PaySalarayDto() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getStatus() {
        return status;
    }



    public void setStatus(String status) {
        this.status = status;
    }

    public float getDeduction() {
        return deduction;
    }

    public void setDeduction(float deduction) {
        this.deduction = deduction;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getUnpaidLeaves() {
        return unpaidLeaves;
    }

    public void setUnpaidLeaves(int unpaidLeaves) {
        this.unpaidLeaves = unpaidLeaves;
    }

    public float getBasic() {
        return basic;
    }

    public void setBasic(float basic) {
        this.basic = basic;
    }

    public float getHouseRent() {
        return houseRent;
    }

    public void setHouseRent(float houseRent) {
        this.houseRent = houseRent;
    }

    public float getMedical() {
        return medical;
    }

    public void setMedical(float medical) {
        this.medical = medical;
    }

    public float getConveyance() {
        return conveyance;
    }

    public void setConveyance(float conveyance) {
        this.conveyance = conveyance;
    }
}
