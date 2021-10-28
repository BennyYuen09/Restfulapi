package com.afs.restfulapi.company;

import com.afs.restfulapi.exception.CompanyNotFoundException;
import com.afs.restfulapi.employee.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getCompanyList() {
        return this.companyService.findAll();
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") Integer id) {
        return this.companyService.findById(id);
    }

    @GetMapping("/{id}/employees")
    public List<Employee> getEmployeeListInCompanyById(@PathVariable("id") Integer id) {
        return this.companyService.getEmployeeListInCompanyById(id);
    }

    @RequestMapping(params = {"page", "pageSize"}, method = RequestMethod.GET)
    public List<Company> getCompanyListByPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                              @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return this.companyService.getCompanyListByPage(page, pageSize).toList();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Company addCompany(@RequestBody Company company) {
        return this.companyService.addCompany(company);
    }

    @PutMapping("/{id}")
    public Company updateCompanyById(@PathVariable("id") Integer id, @RequestBody Company company) {
        return this.companyService.updateCompanyById(id, company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable("id") Integer id) {
        boolean isRemoved;

        try {
            isRemoved = this.companyService.deleteCompanyById(id);
        } catch (CompanyNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        if (isRemoved) {
            return new ResponseEntity<>("Deleted Company ID: " + " ID:  " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }
}
