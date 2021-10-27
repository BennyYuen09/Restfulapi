package com.afs.restfulapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private final List<Employee> employeeList;

    public EmployeeRepository() {
        this.employeeList = new ArrayList<>();

        employeeList.add(new Employee("Benny", 22, "male", 18000));
        employeeList.add(new Employee("Peter", 22, "male", 18000));
        employeeList.add(new Employee("Mary", 18, "female", 20000));
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException{
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
        employeeList.add(employee);
        return employee;
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        Employee originEmployee = getEmployeeById(id);

        originEmployee.updateData(employee);
        return originEmployee;
    }

    public boolean deleteEmployeeById(Integer id) throws EmployeeNotFoundException{
        Employee employee = getEmployeeById(id);
        return this.employeeList.remove(employee);
    }
}
