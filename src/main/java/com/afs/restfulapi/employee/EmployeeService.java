package com.afs.restfulapi.employee;

import com.afs.restfulapi.exception.EmployeeNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository newEmployeeRepository) {
        this.employeeRepository = newEmployeeRepository;
    }

    public List<Employee> getEmployeeList() {
        return this.employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return this.employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    //todo replace return type with list
    public List<Employee> getEmployeeListByPage(int page, int pageSize) {
        return this.employeeRepository
                .findAll(PageRequest.of(page, pageSize))
                .getContent();
    }

    public List<Employee> getEmployeeListByGender(String gender) {
        return this.employeeRepository.findAllByGender(gender);
    }

    public Employee addEmployee(Employee employee) {
        return this.employeeRepository.save(employee);
    }

    public Employee updateEmployee(Integer id, Employee update) {
        Employee employee = getEmployeeById(id);
        if (update.getName() != null) {
            employee.setName(update.getName());
        }
        if (update.getAge() != null) {
            employee.setAge(update.getAge());
        }
        if (update.getGender() != null) {
            employee.setGender(update.getGender());
        }
        if (update.getSalary() != null) {
            employee.setSalary(update.getSalary());
        }
        if (update.getCompanyId() != null){
            employee.setCompanyId(update.getCompanyId());
        }
        return this.employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Integer id) {
        this.employeeRepository.deleteById(id);
    }
}
