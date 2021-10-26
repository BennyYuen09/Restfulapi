package com.afs.restfulapi;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private List<Employee> employeeList;

    public EmployeeRepository() {
        this.employeeList = new ArrayList<>();

        employeeList.add(new Employee(1, "Benny", 22, "male", 18000));
        employeeList.add(new Employee(2, "Peter", 22, "male", 18000));
        employeeList.add(new Employee(3, "Mary", 18, "female", 20000));
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public Employee getEmployeeById(Integer id){
        return employeeList
                .stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }
}
