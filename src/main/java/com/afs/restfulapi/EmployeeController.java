package com.afs.restfulapi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController() {
        employeeRepository = new EmployeeRepository();
    }

    @GetMapping
    public List<Employee> getEmployeeList() {
        return this.employeeRepository.getEmployeeList();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Integer id) {
        return this.employeeRepository.getEmployeeById(id);
    }

    @RequestMapping(params = {"page", "pageSize"}, method = RequestMethod.GET)
    public List<Employee> getEmployeeListByPage(@RequestParam(value = "page") int page,
                                                @RequestParam(value = "pageSize") int pageSize) {
        return this.employeeRepository.getEmployeeListByPage(page, pageSize);
    }

    @RequestMapping(params = {"gender"}, method = RequestMethod.GET)
    public List<Employee> getEmployeeListByGender(@RequestParam(value = "gender") String gender) {
        return this.employeeRepository.getEmployeeListByGender(gender);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return this.employeeRepository.addEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployeeById(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return this.employeeRepository.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Integer id) {
        boolean isRemoved = this.employeeRepository.deleteEmployeeById(id);
        if (isRemoved) {
            return new ResponseEntity<>("Delete Employee ID: " + id, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
