package com.management.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class EmployeeManagement {
    public static void main(String[] args) {
        SpringApplication.run(EmployeeManagement.class, args);
    }
}
