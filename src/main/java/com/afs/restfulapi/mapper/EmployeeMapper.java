package com.afs.restfulapi.mapper;

import com.afs.restfulapi.dto.EmployeeRequest;
import com.afs.restfulapi.dto.EmployeeResponse;
import com.afs.restfulapi.employee.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest employeeRequest) {
        return new Employee(
                null,
                employeeRequest.getName(),
                employeeRequest.getAge(),
                employeeRequest.getGender(),
                employeeRequest.getSalary(),
                employeeRequest.getCompanyId()
        );
    }

    public EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(employee);
    }
}
