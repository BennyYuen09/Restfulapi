package com.afs.restfulapi;

import java.util.List;

public class Company {
    private static int newID = 1;

    private Integer id;
    private String name;
    private List<Employee> employeeList;

    public Company(String name, List<Employee> employeeList) {
        this.id = newID;
        this.name = name;
        this.employeeList = employeeList;

        newID++;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void updateData(Company company) {
        this.name = company.name;
        this.employeeList = company.employeeList;
    }
}
