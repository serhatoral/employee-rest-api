package dev.serhat.employeeapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import dev.serhat.employeeapi.models.DeptEmp;
import dev.serhat.employeeapi.models.EmpDeptKey;


public interface EmpDeptRepository extends JpaRepository<DeptEmp,EmpDeptKey> {
    
   

}
