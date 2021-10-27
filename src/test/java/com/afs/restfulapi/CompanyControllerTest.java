package com.afs.restfulapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void Setup(){
        companyRepository.reset();
    }

    @Test
    void should_get_company_list_when_get_company_list_given_a_company_repository() throws Exception{
        //given
        EmployeeRepository employeeRepository = new EmployeeRepository();

        Employee employee1 = new Employee("Benny", 19, "male", 20000);
        Employee employee2 = new Employee("Tommy", 22, "male", 20000);
        Employee employee3 = new Employee("Mary", 22, "gmale", 100000);

        employeeRepository.addEmployee(employee1);
        employeeRepository.addEmployee(employee2);
        employeeRepository.addEmployee(employee3);

        Company company1 = new Company("EA_Trash", Arrays.asList(employee1, employee2));
        Company company2 = new Company("UB_Soft", Arrays.asList(employee2));

        companyRepository.addCompany(company1);
        companyRepository.addCompany(company2);

        //when
        String expected = "[\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"EA_Trash\",\n" +
                "        \"employeeList\": [\n" +
                "    {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Benny\",\n" +
                "        \"age\": 19,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 20000\n" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Tommy\",\n" +
                "        \"age\": 22,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"salary\": 20000\n" +
                "    }\n" +
                "]" +
                "    },\n" +
                "    {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"UB_Soft\",\n" +
                "        \"employeeList\": []\n" +
                "    }\n" +
                "]";
        //when
        ResultActions resultActions = mockMvc.perform(get("/companies"));

        //then

    }
}
