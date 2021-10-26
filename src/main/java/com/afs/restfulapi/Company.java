package com.afs.restfulapi;

import java.util.List;

public class Company {
    private final String name;
    private final List<Employee> employeeList;

    public Company(String name, List<Employee> employeeList){
        this.name = name;
        this.employeeList = employeeList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public String getName() {
        return name;
    }
}
