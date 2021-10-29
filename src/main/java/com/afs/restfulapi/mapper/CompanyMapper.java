package com.afs.restfulapi.mapper;

import com.afs.restfulapi.company.Company;
import com.afs.restfulapi.dto.CompanyRequest;
import com.afs.restfulapi.dto.CompanyResponse;
import com.afs.restfulapi.dto.EmployeeResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapper {
    private EmployeeMapper employeeMapper;

    public Company toEntity(CompanyRequest companyRequest) {
        Company company = new Company();
        BeanUtils.copyProperties(companyRequest, company);
        return company;
    }

    public CompanyResponse toResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(company.getId());
        companyResponse.setName(company.getName());

        List<EmployeeResponse> employeeResponseList =
                company.getEmployees()
                        .stream()
                        .map(employeeMapper::toResponse)
                        .collect(Collectors.toList())
                ;
        companyResponse.setEmployees(employeeResponseList);
        return companyResponse;
    }
}
