package dev.serhat.employeeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.serhat.employeeapi.models.Department;

public interface DepartmentRepository extends JpaRepository<Department,String> {
    
}
