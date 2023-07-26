package com.project.employee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.employee.data.dto.EmployeeRequestDto;
import com.project.employee.data.dto.EmployeeResponseDto;
import com.project.employee.data.entity.Employee;
import com.project.employee.data.mapper.EmployeeMapper;
import com.project.employee.data.repository.EmployeeRepository;
import com.project.employee.service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private String managerCode;

    public List<EmployeeResponseDto> saveAll(List<EmployeeRequestDto> employeesDto) {
        List<Employee> employees = employeeMapper.toEmployees(employeesDto);
        employees.forEach(employee -> employee.setManager(findManager(employee.getUniqueCode(), employeesDto)));

        try {
            return employeeMapper.toEmployeesDto(employeeRepository.saveAll(employees));
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    public Employee findManager(String uniqueCode, List<EmployeeRequestDto> employeesDto) {
        return employeesDto.stream()
                .filter(dto -> dto.managerId() != null)
                .filter(dto -> dto.uniqueCode().equals(uniqueCode))
                .findFirst()
                .flatMap(dto -> employeeRepository.findById(dto.managerId()))
                .orElse(null);
    }

    public EmployeeResponseDto getEmployee(Long id) {
        return employeeMapper.toEmployeeDto(
                employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException())
        );
    }

    public List<EmployeeResponseDto> getAll() {
        return employeeMapper.toEmployeesDto(employeeRepository.findAll());
    }

    public Double getSubordinatesSalarySum(String uniqueCode) {
        Employee employee = employeeRepository.findByUniqueCode(uniqueCode);
        if (employee == null) {
            return null;
        }
        return getSubordinatesSalarySum(employee);
    }

    private Double getSubordinatesSalarySum(Employee manager) {
        if (managerCode == null) {
            managerCode = manager.getUniqueCode();
        }
        List<Employee> subordinates = employeeRepository.findByManager(manager);
        return subordinates.stream()
                .mapToDouble(subordinate ->
                {
                    if (subordinate.getUniqueCode() != null && subordinate.getUniqueCode().equals(managerCode)) {
                        return 0;
                    } else {
                        return subordinate.getSalary() + getSubordinatesSalarySum(subordinate);
                    }
                })
                .sum();
    }

}







