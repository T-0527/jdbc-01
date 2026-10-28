package com.aaa.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private Integer empId;
    private String empName;
    private String gender;
    private Integer age;
    private String dept;
    private String job;
    private Double salary;
    private Date hiredate;   // 注意：课程里是全小写 d
    private String phone;
    private String email;
}