package com.project.employee.controller.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.employee.data.dto.EmployeeRequestDto;
import com.project.employee.data.dto.EmployeeResponseDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Employee")
@RequestMapping(path = EmployeeApi.ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
public interface EmployeeApi {
    String ENDPOINT = "/api/v1/employee";

    @Operation(summary = "Add single or multiple Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "OK, employee saved.",
                    content = @Content(schema = @Schema(implementation = EmployeeResponseDto.class)))
    })
    @PostMapping
    ResponseEntity<List<EmployeeResponseDto>> addEmployees(@RequestBody List<EmployeeRequestDto> employees);

    @Operation(summary = "Find all Employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK, employees founded.",
                    content = @Content(schema = @Schema(implementation = EmployeeResponseDto.class)))
    })
    @GetMapping
    ResponseEntity<List<EmployeeResponseDto>> getAllEmployees();

    @Operation(summary = "Get Employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK, employee founded.",
                    content = @Content(schema = @Schema(implementation = EmployeeResponseDto.class)))
    })
    @GetMapping("/{id}")
    ResponseEntity<EmployeeResponseDto> getEmployee(@PathVariable Long id);

    @Operation(summary = "Get sum of all subordinates for specific Employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK, sum calculated.")
    })
    @GetMapping("{uniqueCode}/subordinates/salarySum")
    ResponseEntity<Double> getSubordinatesSalarySum(@PathVariable String uniqueCode);
}
