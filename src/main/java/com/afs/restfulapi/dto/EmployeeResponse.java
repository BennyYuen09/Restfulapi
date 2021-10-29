package com.afs.restfulapi.dto;

import com.afs.restfulapi.employee.Employee;

public class EmployeeResponse {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;

    public EmployeeResponse() {
    }

    public EmployeeResponse(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.age = employee.getAge();
        this.gender = employee.getGender();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
