package com.management.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "employee_salary_info")
public class EmployeeSalaryInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 25)
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


    public EmployeeSalaryInfo() {
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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
}
