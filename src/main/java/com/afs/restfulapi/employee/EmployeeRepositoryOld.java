package com.afs.restfulapi.employee;

import com.afs.restfulapi.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepositoryOld {
//    private int newId = 1;
//
//    private List<Employee> employeeList;
//
//    public EmployeeRepositoryOld() {
//        this.employeeList = new ArrayList<>();
//    }
//
//    public List<Employee> getEmployeeList() {
//        return employeeList;
//    }
//
//    public Employee getEmployeeById(Integer id) throws EmployeeNotFoundException {
//        return employeeList
//                .stream()
//                .filter(employee -> employee.getId().equals(id))
//                .findFirst()
//                .orElseThrow(EmployeeNotFoundException::new);
//    }
//
//    public List<Employee> getEmployeeListByPage(int page, int pageSize) {
//        return this.employeeList
//                .stream()
//                .skip((long) pageSize * page)
//                .limit(pageSize)
//                .collect(Collectors.toList());
//    }
//
//    public List<Employee> getEmployeeListByGender(String gender) {
//        return employeeList
//                .stream()
//                .filter(employee -> employee.getGender().equals(gender))
//                .collect(Collectors.toList());
//    }
//
//    public Employee addEmployee(Employee employee) {
//        employee.setId(newId);
//        newId++;
//        employeeList.add(employee);
//        return employee;
//    }
//
//    public Employee updateEmployee(Employee employee) {
//        Employee originEmployee = getEmployeeById(employee.getId());
//
//        originEmployee.updateData(employee);
//        return originEmployee;
//    }
//
//    public boolean deleteEmployeeById(Integer id) throws EmployeeNotFoundException{
//        Employee employee = getEmployeeById(id);
//        return this.employeeList.remove(employee);
//    }
//
//    public void reset(){
//        this.employeeList = new ArrayList<>();
//        newId = 1;
//    }
}
