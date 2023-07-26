package com.project.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.employee.controller.api.EmployeeApi;
import com.project.employee.data.dto.EmployeeRequestDto;
import com.project.employee.data.dto.EmployeeResponseDto;
import com.project.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeeApi {

    private final EmployeeService employeeService;

    @Override
    public ResponseEntity<List<EmployeeResponseDto>> addEmployees(@RequestBody List<EmployeeRequestDto> employees) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeeService.saveAll(employees));
    }

    @Override
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @Override
    public ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployee(id));
    }

    @Override
    public ResponseEntity<Double> getSubordinatesSalarySum(@PathVariable String uniqueCode) {
        return ResponseEntity.ok(employeeService.getSubordinatesSalarySum(uniqueCode));
    }
}
