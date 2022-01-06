package com.management.employee.dto;

import com.management.employee.enums.SalaryStatus;

public class EmployeeSalaryInfoDto {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;
    private Integer salaryId;

    private double salary;

    private float unpaidLeaves;
    private float deduction;

    private float basic;
    private float houseRent;
    private float medical;
    private float conveyance;

    private int month;
    private int year;

    private String monthName;

    private String userName;

    private String status = SalaryStatus.PAID.name();

    public EmployeeSalaryInfoDto() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public float getUnpaidLeaves() {
        return unpaidLeaves;
    }

    public void setUnpaidLeaves(float unpaidLeaves) {
        this.unpaidLeaves = unpaidLeaves;
    }

    public float getDeduction() {
        return deduction;
    }

    public void setDeduction(float deduction) {
        this.deduction = deduction;
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

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }
}
