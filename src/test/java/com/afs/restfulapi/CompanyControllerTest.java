package com.afs.restfulapi;

import com.afs.restfulapi.company.Company;
import com.afs.restfulapi.company.CompanyRepository;
import com.afs.restfulapi.dto.EmployeeResponse;
import com.afs.restfulapi.employee.Employee;
import com.afs.restfulapi.employee.EmployeeRepository;
import com.afs.restfulapi.mapper.CompanyMapper;
import com.afs.restfulapi.mapper.EmployeeMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Sql(statements = "alter table employee alter column id restart with 1")
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Sql(statements = "alter table employee alter column id restart with 1")
    @BeforeEach
    void Setup() {
        companyRepository.deleteAll();
        employeeRepository.deleteAll();
    }

    @Test
    void should_get_company_list_when_get_company_list_given_a_company_repository() throws Exception {
        //given
        Company company1 = new Company("Axel");
        Company company2 = new Company("Thoughtworks");

        companyRepository.save(company1);
        companyRepository.save(company2);

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies"));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(company1.getId()))
                .andExpect(jsonPath("$[0].name").value(company1.getName()))

                .andExpect(jsonPath("$[1].id").value(company2.getId()))
                .andExpect(jsonPath("$[1].name").value(company2.getName()))

                .andExpect(jsonPath("$[2]").doesNotExist());
    }

    @Test
    void should_return_correct_company_when_get_by_id_given_id() throws Exception {
        //given
        Company company1 = new Company("Axel");
        Company company2 = new Company("Thoughtworks");

        companyRepository.save(company1);
        companyRepository.save(company2);

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies/" + company1.getId()));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(company1.getId()))
                .andExpect(jsonPath("$.name").value(company1.getName()));
    }

    @Test
    void should_return_correct_company_page_when_get_page_given_page_and_page_size() throws Exception {
        //given
        Company company1 = new Company("Axel");
        Company company2 = new Company("Thoughtworks");
        Company company3 = new Company("EA_trash");

        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies?page=1&pageSize=1"));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(company2.getId()))
                .andExpect(jsonPath("$[0].name").value(company2.getName()))

                .andExpect(jsonPath("$[1]").doesNotExist());
    }

    @Test
    void should_return_employee_list_when_get_employee_list_given_company_id() throws Exception {
        //given
        Company company1 = new Company("Axel");
        Company company2 = new Company("Thoughtworks");
        Company company3 = new Company("EA_trash");

        companyRepository.save(company1);
        companyRepository.save(company2);
        companyRepository.save(company3);

        Employee employee1 = new Employee(1, "Benny", 22, "male", 12345, company1.getId());
        Employee employee2 = new Employee(2, "Manny", 22, "male", 67890, company1.getId());
        List<EmployeeResponse> employeeList =
                Arrays.asList(employeeMapper.toResponse(employee1), employeeMapper.toResponse(employee2));

        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        //when
        ResultActions resultActions = mockMvc.perform(get("/companies/" + company1.getId() + "/employees"));
        ObjectMapper objectMapper = new ObjectMapper();

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(employeeList)));
    }

    @Test
    void should_return_company_when_add_company_given_company_info() throws Exception {
        //given
        String companyInfo = "{\n" +
                "   \"name\": \"Thoughtworks\"\n" +
                "}\n";

        //when
        ResultActions resultActions = mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON).content(companyInfo));

        //then
        resultActions.andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Thoughtworks"));
    }

    @Test
    void should_get_company_when_update_company_given_company_info() throws Exception {
        //given
        Company company = new Company("Thoughtworks");
        companyRepository.save(company);

        String updateInfo =
                        "{\n" +
                        "   \"name\": \"SuperHot\"\n" +
                        "}\n";

        //when
        ResultActions resultActions = mockMvc.perform(put("/companies/" + company.getId())
                .contentType(MediaType.APPLICATION_JSON).content(updateInfo));

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(company.getId()))
                .andExpect(jsonPath("$.name").value("SuperHot"))
                .andExpect(jsonPath("$.employees").isEmpty());
    }

    @Test
    void should_get_deleted_success_message_when_delete_company_given_company_id() throws Exception {
        //given
        Company company = new Company("Thoughtworks");
        companyRepository.save(company);

        String expected = "Deleted Company ID: 1";

        //when
        ResultActions resultActions = mockMvc.perform(delete("/companies/1"));

        //then
        resultActions.andExpect(status().isOk()).andExpect(content().string(expected));
    }
}
