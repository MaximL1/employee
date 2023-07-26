package com.project.employee.data.dto;

public record EmployeeResponseDto(Long id,
                                  String name,
                                  String uniqueCode,
                                  Double salary,
                                  Long managerId) {
}
