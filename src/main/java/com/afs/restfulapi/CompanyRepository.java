package com.afs.restfulapi;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private final List<Company> companyList;

    public CompanyRepository(){
        this.companyList = new ArrayList<>();
        companyList.add(new Company(1, "APP", new EmployeeRepository().getEmployeeList()));
        companyList.add(new Company(2, "UB_Soft", new ArrayList<>()));
        companyList.add(new Company(3, "EA_Trash", new EmployeeRepository().getEmployeeList()));
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public Company getCompanyById(Integer id){
        return companyList
                .stream()
                .filter(company -> company.getId().equals(id))
                .findFirst()
                .orElseThrow(CompanyNotFoundException::new);
    }
}
