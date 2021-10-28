package com.afs.restfulapi.company;

import com.afs.restfulapi.exception.CompanyNotFoundException;
import com.afs.restfulapi.exception.EmployeeNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CompanyRepositoryOld {
    private int newId = 1;

    private List<Company> companyList;

    public CompanyRepositoryOld() {
        this.companyList = new ArrayList<>();
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public Company getCompanyById(Integer id) throws CompanyNotFoundException {
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
        company.setId(newId);
        newId++;
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

    public void reset(){
        this.companyList = new ArrayList<>();
        newId = 1;
    }
}
