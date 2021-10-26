package com.afs.restfulapi;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeControllerTest {
    @Test
    void should_return_employee_list_when_get_employee_list_given_a_employee_repository() {
        //given
        List<Employee> expected = new EmployeeRepository().getEmployeeList();

        //when
        List<Employee> actual = new EmployeeController().getEmployeeList();

        //then
        assertEquals(expected, actual);
    }
}
