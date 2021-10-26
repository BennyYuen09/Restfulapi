package com.afs.restfulapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @GetMapping
    public List<Company> getCompanyList(){
        return new CompanyRepository().getCompanyList();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") Integer id){
        return new CompanyRepository().getCompanyById(id);
    }
}
