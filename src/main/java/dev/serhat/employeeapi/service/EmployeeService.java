package dev.serhat.employeeapi.service;




import java.util.List;
import java.util.Optional;

import dev.serhat.employeeapi.core.utilities.results.DataResult;
import dev.serhat.employeeapi.core.utilities.results.Result;
import dev.serhat.employeeapi.models.Department;
import dev.serhat.employeeapi.models.Employee;
import dev.serhat.employeeapi.models.dtos.BaseEmployee;
import dev.serhat.employeeapi.models.dtos.EmployeeSalaries;
import dev.serhat.employeeapi.models.dtos.EmployeeTitles;

public interface EmployeeService {

    DataResult<Optional<Employee>> getEmployee(int id);
    DataResult<List<Employee>> getAllEmployees(int pageNo, int pageSize);
    DataResult<List<Employee>> getByFirstNameContains(String firstName);
    Result add(Employee employee);

    DataResult<List<Department>> getAllDept();
    DataResult<Optional<Department>> getDept(String deptNo);

    DataResult<Optional<BaseEmployee>> getBaseEmployeeById(int id);

    DataResult<EmployeeSalaries> getEmployeeWithSalariesById(int id);

    DataResult<List<EmployeeTitles>> getEmployeeTitlesByFirstName(String firstName);
    
}
