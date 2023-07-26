package com.project.employee.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.employee.data.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUniqueCode(String uniqueCode);
    List<Employee> findByManager(Employee manager);
}
