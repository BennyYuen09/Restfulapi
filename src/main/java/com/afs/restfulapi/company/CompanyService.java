package com.afs.restfulapi.company;

import com.afs.restfulapi.employee.Employee;
import com.afs.restfulapi.employee.EmployeeRepository;
import com.afs.restfulapi.exception.CompanyNotFoundException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository){
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    public Company findById(Integer id){
        return companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    public List<Employee> getEmployeeListInCompanyById(int id) {
        return employeeRepository.findAllByCompanyId(id);
    }

    public PageImpl<Company> getCompanyListByPage(int page, int pageSize){
        return (PageImpl<Company>) companyRepository.findAll(PageRequest.of(page, pageSize));
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompanyById(int id, Company update){
        Company originCompany = findById(id);
        if (update.getName() != null) {
            originCompany.setName(update.getName());
        }
        if (update.getEmployees() != null){
            originCompany.setEmployees(update.getEmployees());
        }
        return companyRepository.save(originCompany);
    }

    public boolean deleteCompanyById(Integer id) {
        this.companyRepository.deleteById(id);
        return true;
    }
}
