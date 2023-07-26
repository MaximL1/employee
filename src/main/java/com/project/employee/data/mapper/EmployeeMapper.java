package com.project.employee.data.mapper;

import java.util.List;

import org.mapstruct.*;

import com.project.employee.data.dto.EmployeeRequestDto;
import com.project.employee.data.dto.EmployeeResponseDto;
import com.project.employee.data.entity.Employee;

@Mapper
public interface EmployeeMapper {

    List<EmployeeResponseDto> toEmployeesDto(List<Employee> employees);

    @Mapping(target = "managerId", source = "manager.id")
    EmployeeResponseDto toEmployeeDto(Employee employee);

    Employee toEmployee(EmployeeRequestDto requestDto);

    List<Employee> toEmployees(List<EmployeeRequestDto> requestsDto);

}
