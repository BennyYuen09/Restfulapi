package com.afs.restfulapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepository {
    private final List<Company> companyList;

    public CompanyRepository() {
        this.companyList = new ArrayList<>();
        companyList.add(new Company("APP", new EmployeeRepository().getEmployeeList()));
        companyList.add(new Company("UB_Soft", new ArrayList<>()));
        companyList.add(new Company("EA_Trash", new EmployeeRepository().getEmployeeList()));
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public Company getCompanyById(Integer id) throws CompanyNotFoundException{
        return companyList
                .stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(CompanyNotFoundException::new);
    }

    public List<Company> getCompanyByPage(int page, int pageSize) {
        return this.companyList
                .stream()
                .skip((long) pageSize * page)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Company addCompany(Company company) {
        companyList.add(company);
        return company;
    }

    public Company updateCompany(Integer id, Company company) {
        Company companyEmployee = getCompanyById(id);
        if (!companyEmployee.getId().equals(company.getId())) {
            throw new EmployeeNotFoundException();
        }
        companyEmployee.updateData(company);
        return companyEmployee;
    }

    public boolean deleteEmployeeById(Integer id) throws CompanyNotFoundException{
        Company company = this.getCompanyById(id);
        return this.companyList.remove(company);
    }
}
