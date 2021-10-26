package com.afs.restfulapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public Employee getEmployeeById(Integer id) {
        return employeeList
                .stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getEmployeeListByPage(int page, int pageSize) {
        List<Employee> employeeList =
                this.employeeList
                .stream()
                .skip(pageSize * page)
                .limit(pageSize)
                .collect(Collectors.toList());
        return employeeList;
    }

    public List<Employee> getEmployeeListByGender(String gender){
        return employeeList
                .stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employee addEmployee(Employee employee){
        Integer newID = employeeList
                        .stream()
                        .mapToInt(Employee::getId)
                        .max()
                        .orElse(0) + 1;
        employee.setId(newID);
        return employee;
    }
}
