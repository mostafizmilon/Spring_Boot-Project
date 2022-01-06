package com.management.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "employee_salary_structure")
public class SalaryStructureInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", length = 25)
    private Integer id;

    private float basic;
    private float houseRent;
    private float medical;
    private float conveyance;

    public SalaryStructureInfo() {
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
