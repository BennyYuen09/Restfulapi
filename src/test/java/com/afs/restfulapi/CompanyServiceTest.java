package com.afs.restfulapi;

import com.afs.restfulapi.company.Company;
import com.afs.restfulapi.company.CompanyRepository;
import com.afs.restfulapi.company.CompanyService;
import com.afs.restfulapi.employee.Employee;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(SpringExtension.class)
public class CompanyServiceTest {
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private CompanyService companyService;

    @Test
    void should_return_all_company_when_get_company_list_given_3_company() {
        //given
        List<Company> expected = Arrays.asList(
                new Company("Axel"),
                new Company("UB_soft"),
                new Company("EA_trash")
        );
        when(companyRepository.findAll()).thenReturn(expected);

        //when
        List<Company> actual = companyService.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_correct_company_when_find_by_id_given_company_id() {
        //given
        Company expected = new Company("Axel");
        when(companyRepository.findById(any())).thenReturn(Optional.of(expected));

        //when
        Company actual = companyService.findById(1);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_employee_list_when_get_employee_list_given_company_id() {
        //given
        List<Employee> expected = Arrays.asList(
                new Employee("Benny", 19, "male", 20000),
                new Employee("Tommy", 22, "male", 20000),
                new Employee("Mary", 22, "female", 100000)
        );
        when(employeeRepository.findAllByCompanyId(1)).thenReturn(expected);

        //when
        List<Employee> actual = companyService.getEmployeeListInCompanyById(1);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_correct_company_page_when_get_page_given_page_and_page_size() {
        List<Company> expected = Collections.singletonList(
                new Company("Axel")
        );

        when(companyRepository.findAll(PageRequest.of(1, 1))).thenReturn(new PageImpl<>(expected));

        //when
        List<Company> actual = companyService.getCompanyListByPage(1, 1);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_company_when_add_company_given_company() {
        Company expected = new Company("Axel");
        when(companyRepository.save(expected)).thenReturn(expected);

        //when
        Company actual = companyService.addCompany(expected);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_updated_company_when_update_company_by_id_given_id_and_company_info() {
        Company company = new Company("Axel");
        when(companyRepository.findById(any())).thenReturn(Optional.of(company));

        Company updateInfo = new Company("Axel_Super");
        Company updated = new Company("Axel_Super");

        when(companyRepository.save(any(Company.class))).thenReturn(updated);

        //when
        Company actual = companyService.updateCompanyById(1, updateInfo);

        //then
        assertEquals("Axel_Super", actual.getName());
    }

    @Test
    void should_return_delete_message_when_delete_employee_given_id() {
        doNothing().when(companyRepository).deleteById(1);

        //when
        companyService.deleteCompanyById(1);

        //then
        verify(companyRepository, times(1)).deleteById(1);
    }
}
