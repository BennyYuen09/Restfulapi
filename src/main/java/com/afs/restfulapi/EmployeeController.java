package com.afs.restfulapi;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @GetMapping
    public List<Employee> getEmployeeList() {
        return new EmployeeRepository().getEmployeeList();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") int id) {
        return new EmployeeRepository().getEmployeeById(id);
    }

    @RequestMapping(params = {"page", "pageSize"}, method = RequestMethod.GET)
    public List<Employee> getEmployeeListByPage(@RequestParam(value = "page") int page,
                                                @RequestParam(value = "pageSize") int pageSize) {
        return new EmployeeRepository().getEmployeeListByPage(page, pageSize);
    }

    @RequestMapping(params = {"gender"}, method = RequestMethod.GET)
    public List<Employee> getEmployeeListByGender(@RequestParam(value = "gender") String gender){
        return new EmployeeRepository().getEmployeeListByGender(gender);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee){
        return new EmployeeRepository().addEmployee(employee);
    }
}
