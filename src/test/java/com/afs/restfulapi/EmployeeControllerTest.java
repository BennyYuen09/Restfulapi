package com.afs.restfulapi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeControllerTest {
    @Test
    void should_return_employee_list_when_get_employee_list_given_employee_repository() {
        //given
        List<Employee> expected = new EmployeeRepository().getEmployeeList();

        //when
        List<Employee> actual = new EmployeeController().getEmployeeList();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_correct_employee_when_get_employee_list_given_employee_id() {
        //given
        Employee expected = new EmployeeRepository().getEmployeeById(1);

        //when
        Employee actual = new EmployeeController().getEmployeeById(1);

        //then
        assertEquals(expected, actual);
    }
}
