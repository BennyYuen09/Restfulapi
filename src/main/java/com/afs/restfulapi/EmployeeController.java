package com.afs.restfulapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @GetMapping
    public List<Employee> getEmployeeList(){
        return new EmployeeRepository().getEmployeeList();
    }
}
