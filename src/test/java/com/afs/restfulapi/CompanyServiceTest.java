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
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

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
}
