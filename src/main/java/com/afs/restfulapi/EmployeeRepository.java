package com.afs.restfulapi;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeRepository {
    private final List<Employee> employeeList;

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
                .filter(employee -> employee.getId().equals(id))
                .findFirst()
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public List<Employee> getEmployeeListByPage(int page, int pageSize) {
        return this.employeeList
                .stream()
                .skip((long) pageSize * page)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public List<Employee> getEmployeeListByGender(String gender) {
        return employeeList
                .stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employee addEmployee(Employee employee) {
        Integer newID = employeeList
                .stream()
                .mapToInt(Employee::getId)
                .max()
                .orElse(0) + 1;
        employee.setId(newID);
        employeeList.add(employee);
        return employee;
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        Employee originEmployee = getEmployeeById(id);
        if (!originEmployee.getId().equals(employee.getId())) {
            throw new EmployeeNotFoundException();
        }
        originEmployee.updateData(employee);
        return originEmployee;
    }

    public boolean deleteEmployeeById(Integer id){
        Employee employee = getEmployeeById(id);
        return this.employeeList.remove(employee);
    }
}
