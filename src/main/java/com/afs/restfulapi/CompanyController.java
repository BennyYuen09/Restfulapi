package com.afs.restfulapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeeListInCompanyById(@PathVariable("id") Integer id){
        return new CompanyRepository().getCompanyById(id).getEmployeeList();
    }

    @RequestMapping(params = {"page", "pageSize"}, method = RequestMethod.GET)
    public List<Company> getCompanyListByPage(@RequestParam(value = "page") int page,
                                                @RequestParam(value = "pageSize") int pageSize) {
        return new CompanyRepository().getCompanyByPage(page, pageSize);
    }

    @PostMapping
    public Company addCompany(@RequestBody Company company){
        return new CompanyRepository().addCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompanyById(@PathVariable("id") Integer id, @RequestBody Company company){
        return new CompanyRepository().updateCompany(id, company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable("id") Integer id){
        boolean isRemoved = new CompanyRepository().deleteEmployeeById(id);
        if (isRemoved){
            return new ResponseEntity<>("Delete Company ID: " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
