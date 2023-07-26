package com.project.employee.data.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.employee.data.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByUniqueCode(String uniqueCode);
    List<Employee> findByManager(Employee manager);

    @Cacheable("employees")
    Optional<Employee> findById(Long id);
}
