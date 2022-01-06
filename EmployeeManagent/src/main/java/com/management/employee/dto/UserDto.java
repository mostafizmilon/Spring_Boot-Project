package com.management.employee.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UserDto {
    private Integer id;

    private String email;

    private String full_name;

    private String address;

    private boolean enabled;

    @NotBlank(message = "Designation can not be empty.")
    private String designation;
    @Min(value = 1, message = "Must be greater than 0")
    private double salary =0.0;
    @Min(value = 1, message = "Must be greater than 0")
    public int leaves = 0;

    public UserDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getLeaves() {
        return leaves;
    }

    public void setLeaves(int leaves) {
        this.leaves = leaves;
    }
}
