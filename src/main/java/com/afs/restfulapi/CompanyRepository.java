package com.afs.restfulapi;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private final List<Company> companyList;

    public CompanyRepository(){
        this.companyList = new ArrayList<>();
        companyList.add(new Company("APP", new EmployeeRepository().getEmployeeList()));
        companyList.add(new Company("UB_Soft", new ArrayList<>()));
        companyList.add(new Company("EA_Trash", new EmployeeRepository().getEmployeeList()));
    }

    public List<Company> getCompanyList() {
        return companyList;
    }
}
