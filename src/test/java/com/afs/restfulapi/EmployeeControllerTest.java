package com.afs.restfulapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void Init(){

    }

    @Test
    void should_return_all_employee_when_get_employee_list_given_2_employee() throws Exception{
        //given
        Employee employee1 = new Employee("Benny", 19, "male", 20000);
        Employee employee2 = new Employee("Tommy", 22, "male", 20000);

        employeeRepository.addEmployee(employee1);
        employeeRepository.addEmployee(employee2);

        String expected = "[\n" +
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
                "]";

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees"));

        //then
        resultActions.andExpect(status().isOk()).andExpect(content().json(expected));
    }

    @Test
    void should_return_correct_employee_when_get_by_id_given_id() throws Exception{
        //given
        Employee employee1 = new Employee("Benny", 19, "male", 20000);
        Employee employee2 = new Employee("Tommy", 22, "male", 20000);

        employeeRepository.addEmployee(employee1);
        employeeRepository.addEmployee(employee2);

        String expected =
                "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Benny\",\n" +
                "    \"age\": 19,\n" +
                "    \"gender\": \"male\",\n" +
                "    \"salary\": 20000\n" +
                "}\n";

        //when
        ResultActions resultActions = mockMvc.perform(get("/employees/1"));

        //then
        resultActions.andExpect(status().isOk()).andExpect(content().json(expected));
    }

}
