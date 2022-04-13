package dev.serhat.employeeapi.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import dev.serhat.employeeapi.models.Employee;
import dev.serhat.employeeapi.models.dtos.BaseEmployee;


public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    
    List<Employee> getByFirstNameContains(String firstName);
    List<Employee> getByFirstNameStartsWith(String firstName);

    @Query("Select new dev.serhat.employeeapi.models.dtos.BaseEmployee"
    + "(e.id, e.birthDate, e.firstName, e.lastName, e.gender, e.hireDate) From Employee e WHERE e.id = :id")
    Optional<BaseEmployee> getBaseEmployeeById(int id);
    
    


}
