package com.afs.restfulapi.company;

import com.afs.restfulapi.employee.Employee;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "companyId")
    private List<Employee> employees;

    public Company(){
        employees = new ArrayList<>();
    }

    public Company(String name) {
        this.id = null;
        this.name = name;
        this.employees = new ArrayList<>();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee){
        employee.setCompanyId(this.id);
        employees.add(employee);
    }

    public void setAllEmployeeCompanyId(){
        employees.forEach(
                employee -> employee.setCompanyId(id)
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
