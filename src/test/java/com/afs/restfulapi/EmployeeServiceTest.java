package com.afs.restfulapi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    @Test
    void should_return_all_employee_when_get_employee_list_given_3_employee(){
        EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
        EmployeeService employeeService = new EmployeeService(employeeRepository);
        List<Employee> employeeList = Arrays.asList(
                new Employee("Benny", 19, "male", 20000),
                new Employee("Tommy", 22, "male", 20000),
                new Employee("Mary", 22, "female", 100000)
        );
        when(employeeRepository.getEmployeeList()).thenReturn(employeeList);

        //when
        List<Employee> actual = employeeService.getEmployeeList();

        //then
        assertEquals(employeeList, actual);
    }
}
