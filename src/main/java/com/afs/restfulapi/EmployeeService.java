package com.afs.restfulapi;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeeList() {
        return this.employeeRepository.getEmployeeList();
    }

    public Employee getEmployeeById(int id) {
        return this.employeeRepository.getEmployeeById(id);
    }
}