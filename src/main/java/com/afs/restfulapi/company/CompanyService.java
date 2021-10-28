package com.afs.restfulapi.company;

import com.afs.restfulapi.employee.Employee;
import com.afs.restfulapi.exception.CompanyNotFoundException;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository newCompanyRepository;

    public CompanyService(CompanyRepository newCompanyRepository){
        this.newCompanyRepository = newCompanyRepository;
    }

    public List<Company> findAll(){
        return newCompanyRepository.findAll();
    }

    public Company findById(Integer id){
        return newCompanyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
    }

    public List<Employee> getEmployeeListInCompanyById(int id) {
        return findById(id).getEmployees();
    }

    public PageImpl<Company> getCompanyListByPage(int page, int pageSize){
        return (PageImpl<Company>) newCompanyRepository.findAll(PageRequest.of(page, pageSize));
    }

    public Company addCompany(Company company) {
        return newCompanyRepository.save(company);
    }

    public Company updateCompanyById(int id, Company update){
        Company originCompany = findById(id);
        if (update.getName() != null) {
            originCompany.setName(update.getName());
        }
        if (update.getEmployees() != null){
            originCompany.setEmployees(update.getEmployees());
        }
        return newCompanyRepository.save(originCompany);
    }

    public boolean deleteCompanyById(Integer id) {
        this.newCompanyRepository.deleteById(id);
        return true;
    }
}
