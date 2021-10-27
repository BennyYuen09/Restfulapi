package com.afs.restfulapi;

public class Employee {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;

    public Employee(String name, Integer age, String gender, Integer salary) {
        this.id = null;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public Employee(Integer id, String name, Integer age, String gender, Integer salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void updateData(Employee employee) {
        this.name = (employee.name != null)? employee.name : this.name;
        this.age = (employee.age != null)? employee.age : this.age;
        this.gender = (employee.gender != null)? employee.gender : this.gender;
        this.salary = (employee.salary != null)? employee.salary : this.salary;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o instanceof Employee){
            Employee employee = (Employee) o;
            return this.id.equals(((Employee) o).id);
        }
        return false;
    }
}
