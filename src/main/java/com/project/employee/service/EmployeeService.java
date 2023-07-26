package com.project.employee.service;

import java.util.List;

import com.project.employee.data.dto.EmployeeRequestDto;
import com.project.employee.data.dto.EmployeeResponseDto;
import com.project.employee.data.entity.Employee;

public interface EmployeeService {

    List<EmployeeResponseDto> saveAll(List<EmployeeRequestDto> employeesDto);

    Employee findManager(String uniqueCode, List<EmployeeRequestDto> employeesDto);

    EmployeeResponseDto getEmployee(Long id);

    List<EmployeeResponseDto> getAll();

    Double getSubordinatesSalarySum(String uniqueCode);
}
