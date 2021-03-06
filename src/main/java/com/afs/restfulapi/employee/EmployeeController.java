package com.afs.restfulapi.employee;

import com.afs.restfulapi.dto.EmployeeRequest;
import com.afs.restfulapi.dto.EmployeeResponse;
import com.afs.restfulapi.mapper.EmployeeMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }

    @GetMapping
    public List<EmployeeResponse> getEmployeeList() {
        return employeeService
                .getEmployeeList()
                .stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList())
                ;
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable("id") Integer id) {
        return employeeMapper.toResponse(employeeService.getEmployeeById(id));
    }

    @RequestMapping(params = {"page", "pageSize"}, method = RequestMethod.GET)
    public List<EmployeeResponse> getEmployeeListByPage(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "pageSize", defaultValue = "5") int pageSize
    ) {
        return employeeService
                .getEmployeeListByPage(page, pageSize)
                .stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList())
                ;
    }

    @RequestMapping(params = {"gender"}, method = RequestMethod.GET)
    public List<EmployeeResponse> getEmployeeListByGender(
            @RequestParam(value = "gender", defaultValue = "male") String gender
    ) {
        return employeeService
                .getEmployeeListByGender(gender)
                .stream()
                .map(employeeMapper::toResponse)
                .collect(Collectors.toList())
                ;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public EmployeeResponse addEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.addEmployee(
                employeeMapper.toEntity(employeeRequest)
        );
        return employeeMapper.toResponse(employee);
    }

    @PutMapping("/{id}")
    public EmployeeResponse updateEmployeeById(@PathVariable("id") Integer id, @RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.updateEmployee(
                id, employeeMapper.toEntity(employeeRequest)
        );
        return employeeMapper.toResponse(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Integer id) {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Deleted Employee ID: " + id, HttpStatus.OK);
    }
}
