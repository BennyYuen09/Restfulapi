package com.afs.restfulapi;

import com.afs.restfulapi.employee.Employee;
import com.afs.restfulapi.employee.EmployeeService;
import com.afs.restfulapi.employee.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void should_return_all_employee_when_get_employee_list_given_3_employee() {
        List<Employee> employeeList = Arrays.asList(
                new Employee("Benny", 19, "male", 20000),
                new Employee("Tommy", 22, "male", 20000),
                new Employee("Mary", 22, "female", 100000)
        );
        when(employeeRepository.findAll()).thenReturn(employeeList);

        //when
        List<Employee> actual = employeeService.getEmployeeList();

        //then
        assertEquals(employeeList, actual);
    }

    @Test
    void should_return_correct_employee_when_get_by_id_given_id() {
        Employee employee = new Employee(1,"Benny", 19, "male", 20000, null);
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        //when
        Employee actual = employeeService.getEmployeeById(1);

        //then
        assertEquals(employee, actual);
    }

    @Test
    void should_return_correct_employees_page_when_get_page_given_page_and_page_size() {
        List<Employee> expected = Collections.singletonList(
                new Employee("Tommy", 22, "male", 20000)
        );

        when(employeeRepository.findAll(PageRequest.of(1, 1))).thenReturn(new PageImpl<>(expected));

        //when
        List<Employee> actual = employeeService.getEmployeeListByPage(1, 1);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_employees_list_when_get_by_gender_given_gender() {
        List<Employee> expected = Arrays.asList(
                new Employee("Benny", 19, "male", 20000),
                new Employee("Tommy", 22, "male", 20000)
        );
        when(employeeRepository.findAllByGender("male"))
                .thenReturn(expected);

        //when
        List<Employee> actual = employeeService.getEmployeeListByGender("male");

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_employee_when_add_employee_given_employee() {
        Employee add = new Employee("Manny", 22, "male", 50000);
        Employee expected = new Employee("Manny", 22, "male", 50000);
        expected.setId(1);
        when(employeeRepository.save(add)).thenReturn(expected);

        //when
        Employee actual = employeeService.addEmployee(add);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_updated_employee_when_update_employee_by_id_given_id_and_employee_info() {
        Employee employee = new Employee(1, "Benny", 19, "male", 20000, null);
        when(employeeRepository.findById(any())).thenReturn(Optional.of(employee));

        Employee updateInfo = new Employee(null,null, null, null, 123, null);
        Employee updated = new Employee(1, "Benny", 19, "male", 123, null);

        when(employeeRepository.save(any(Employee.class))).thenReturn(updated);

        //when
        Employee actual = employeeService.updateEmployee(1, updateInfo);

        //then
        assertNotNull(actual);
        assertEquals(1, actual.getId());
        assertEquals("Benny", actual.getName());
        assertEquals("male", actual.getGender());
        assertEquals(19, actual.getAge());
        assertEquals(123, actual.getSalary());
    }

    @Test
    void should_return_delete_message_when_delete_employee_given_id() {
        doNothing().when(employeeRepository).deleteById(1);

        //when
        employeeService.deleteEmployeeById(1);

        //then
        verify(employeeRepository, times(1)).deleteById(1);
    }
}
