package com.afs.restfulapi.company;

import com.afs.restfulapi.dto.CompanyRequest;
import com.afs.restfulapi.dto.CompanyResponse;
import com.afs.restfulapi.dto.EmployeeResponse;
import com.afs.restfulapi.mapper.CompanyMapper;
import com.afs.restfulapi.mapper.EmployeeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private final EmployeeMapper employeeMapper;

    public CompanyController(CompanyService companyService,
                             CompanyMapper companyMapper,
                             EmployeeMapper employeeMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<CompanyResponse> getCompanyList() {
        return this.companyService.findAll()
                .stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList())
                ;
    }

    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable("id") Integer id) {
        return companyMapper.toResponse(companyService.findById(id));
    }

    @GetMapping("/{id}/employees")
    public List<EmployeeResponse> getEmployeeListInCompanyById(@PathVariable("id") Integer id) {
        return companyService.getEmployeeListInCompanyById(id)
                .stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList())
                ;
    }

    @RequestMapping(params = {"page", "pageSize"}, method = RequestMethod.GET)
    public List<CompanyResponse> getCompanyListByPage(@RequestParam(value = "page", defaultValue = "0") int page,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") int pageSize) {
        return companyService.getCompanyListByPage(page, pageSize)
                .stream()
                .map(companyMapper::toResponse)
                .collect(Collectors.toList())
                ;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CompanyResponse addCompany(@RequestBody CompanyRequest companyRequest) {
        return companyMapper.toResponse(
                companyService.addCompany(companyMapper.toEntity(companyRequest))
        );
    }

    @PutMapping("/{id}")
    public CompanyResponse updateCompanyById(
            @PathVariable("id") Integer id,
            @RequestBody CompanyRequest companyRequest
    ) {
        Company company = companyService.updateCompanyById(id, companyMapper.toEntity(companyRequest));
        return companyMapper.toResponse(company);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable("id") Integer id) {
        companyService.deleteCompanyById(id);
        return new ResponseEntity<>("Deleted Company ID: " + id, HttpStatus.OK);
    }
}
