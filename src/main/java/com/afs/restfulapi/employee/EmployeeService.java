package com.afs.restfulapi.employee;

import com.afs.restfulapi.exception.EmployeeNotFoundException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepositoryOld employeeRepository;
    private final EmployeeRepository newEmployeeRepository;

    public EmployeeService(EmployeeRepositoryOld employeeRepository, EmployeeRepository newEmployeeRepository) {
        this.employeeRepository = employeeRepository;
        this.newEmployeeRepository = newEmployeeRepository;
    }

    public List<Employee> getEmployeeList() {
        return this.newEmployeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return this.newEmployeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
    }

    public PageImpl<Employee> getEmployeeListByPage(int page, int pageSize) {
        return (PageImpl<Employee>)this.newEmployeeRepository.findAll(PageRequest.of(page, pageSize));
    }

    public List<Employee> getEmployeeListByGender(String gender) {
        return this.newEmployeeRepository.findAllByGender(gender);
    }

    public Employee addEmployee(Employee employee) {
        return this.newEmployeeRepository.save(employee);
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
        return this.newEmployeeRepository.save(employee);
    }

    public boolean deleteEmployeeById(Integer id) {
        this.newEmployeeRepository.deleteById(id);
        return true;
    }
}
