package com.afs.restfulapi;

public class Employee {
    private static int newID = 1;

    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;

    private Integer companyId;

    public Employee(String name, Integer age, String gender, Integer salary) {
        this.id = newID;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;

        newID++;
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
        this.name = employee.name;
        this.age = employee.age;
        this.gender = employee.gender;
        this.salary = employee.salary;
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
