package com.project.employee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.project.employee.data.dto.EmployeeRequestDto;
import com.project.employee.data.dto.EmployeeResponseDto;
import com.project.employee.data.entity.Employee;
import com.project.employee.data.mapper.EmployeeMapper;
import com.project.employee.data.repository.EmployeeRepository;
import com.project.employee.service.impl.EmployeeServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

class EmployeeServiceTest {

    @Mock
    private EmployeeRepository repository;
    @Mock
    private EmployeeMapper employeeMapper;

    private EmployeeServiceImpl service;
    private Employee employee;
    private EmployeeRequestDto requestDto;
    private EmployeeResponseDto responseDto;

    @BeforeEach
    void setUp() {
        openMocks(this);
        service = new EmployeeServiceImpl(repository, employeeMapper);
        employee = new Employee();
        employee.setId(1L);
        employee.setUniqueCode("1");
        employee.setName("Test");
        employee.setSalary(1000D);

        responseDto = new EmployeeResponseDto(1L, "Test", "1", 1000D, null);
        requestDto = new EmployeeRequestDto("Test", "1", 1000D, null);
    }

    @Test
    void saveAll() {
        when(employeeMapper.toEmployees(List.of(requestDto))).thenReturn(List.of(employee));
        when(employeeMapper.toEmployeesDto(List.of(employee))).thenReturn(List.of(responseDto));
        when(repository.saveAll(List.of(employee))).thenReturn(List.of(employee));

        List<EmployeeResponseDto> result = service.saveAll(List.of(requestDto));

        assertThat(result).contains(responseDto);
    }

    @Test
    void getEmployee() {
        when(employeeMapper.toEmployeeDto(employee)).thenReturn(responseDto);
        when(repository.findById(1L)).thenReturn(Optional.of(employee));

        EmployeeResponseDto result = service.getEmployee(1L);

        assertEquals(responseDto, result);
    }

    @Test
    void getAll() {
        when(employeeMapper.toEmployeesDto(List.of(employee))).thenReturn(List.of(responseDto));
        when(repository.findAll()).thenReturn(List.of(employee));

        List<EmployeeResponseDto> result = service.getAll();

        assertThat(result).contains(responseDto);
    }

    @Test
    void getSubordinatesSalarySum() {
        Employee employee2 = new Employee();
        employee2.setSalary(1000D);
        Employee manager = mock(Employee.class);

        when(repository.findByManager(manager)).thenReturn(List.of(employee, employee2));
        when(repository.findByUniqueCode("1")).thenReturn(manager);

        Double result = service.getSubordinatesSalarySum("1");

        assertEquals(2000D, result);
    }

}
