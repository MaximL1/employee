package com.project.employee.controller;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MvcResult;

import com.project.employee.controller.api.EmployeeApi;
import com.project.employee.data.dto.EmployeeRequestDto;
import com.project.employee.data.dto.EmployeeResponseDto;
import com.project.employee.data.entity.Employee;

import jakarta.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.List;
import java.util.UUID;

@Transactional
@ActiveProfiles(profiles = {"test"})
class EmployeeControllerTest extends AbstractResIT {

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void getAll() throws Exception {
        setupEmployee();

        MvcResult mvcResult = mockMvc.perform(
                        get(EmployeeApi.ENDPOINT)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk())
                .andReturn();

        List<EmployeeResponseDto> result = mapToList(mvcResult, EmployeeResponseDto.class);

        assertEquals(1, result.size());
    }

    @Test
    void addEmployees() throws Exception {
        Employee employee = setupEmployee();
        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto("test2", "2", 10D, employee.getId());

        MvcResult mvcResult = mockMvc.perform(
                        post(EmployeeApi.ENDPOINT)
                                .content(objectMapper.writeValueAsString(List.of(employeeRequestDto)))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isCreated())
                .andReturn();

        List<EmployeeResponseDto> result = mapToList(mvcResult, EmployeeResponseDto.class);

        assertEquals(1, result.size());
        assertNotNull(entityManager.find(Employee.class, 2L));
        assertNotNull(entityManager.find(Employee.class, 1L));
    }

    @Test
    void getEmployee() throws Exception {
        Employee employee = setupEmployee();
        MvcResult mvcResult = mockMvc.perform(
                        get(EmployeeApi.ENDPOINT + "/" + employee.getId())
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk())
                .andReturn();

        EmployeeResponseDto result = mapToType(mvcResult, EmployeeResponseDto.class);

        assertEquals(employee.getUniqueCode(), result.uniqueCode());
    }

    @Test
    void getSubordinatesSalarySum() throws Exception {
        Employee employee1 = setupEmployee();
        Employee employee2 = setupEmployee();
        employee2.setManager(employee1);
        entityManager.persist(employee2);
        Employee employee3 = setupEmployee();
        employee3.setManager(employee2);
        entityManager.persist(employee3);
        Employee employee4 = setupEmployee();
        employee4.setManager(employee2);
        entityManager.persist(employee4);

        MvcResult mvcResult = mockMvc.perform(
                        get(EmployeeApi.ENDPOINT + "/" + employee1.getUniqueCode() + "/subordinates/salarySum")
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpectAll(status().isOk())
                .andReturn();

        Double result = mapToType(mvcResult, Double.class);

        assertEquals(3000D, result);
    }

    private Employee setupEmployee() {
        Employee employee = new Employee();
        employee.setSalary(1000D);
        employee.setName("Test");
        employee.setUniqueCode(UUID.randomUUID().toString());
        return entityManager.persist(employee);
    }
}
