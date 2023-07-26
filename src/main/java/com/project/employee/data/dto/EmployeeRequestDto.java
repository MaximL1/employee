package com.project.employee.data.dto;

import jakarta.annotation.Nonnull;

public record EmployeeRequestDto(String name,
                                 @Nonnull
                                 String uniqueCode,
                                 Double salary,
                                 Long managerId) {
}
