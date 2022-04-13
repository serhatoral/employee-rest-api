package dev.serhat.employeeapi.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev.serhat.employeeapi.core.utilities.results.DataResult;
import dev.serhat.employeeapi.core.utilities.results.Result;
import dev.serhat.employeeapi.models.Department;
import dev.serhat.employeeapi.models.Employee;
import dev.serhat.employeeapi.models.dtos.BaseEmployee;
import dev.serhat.employeeapi.models.dtos.EmployeeSalaries;
import dev.serhat.employeeapi.models.dtos.EmployeeTitles;
import dev.serhat.employeeapi.service.EmployeeService;


@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/getEmployee")
    public DataResult<Optional<Employee>> getEmployee(@RequestParam int id){
        return employeeService.getEmployee(id);
        
    }

    @GetMapping("/getAllEmployees")
    public DataResult<List<Employee>> getAllEmployees(int pageNo, int pageSize){
        return employeeService.getAllEmployees(pageNo, pageSize);
    }
    

    @GetMapping("/getByFirstName")
    public DataResult<List<Employee>> getByFirstNameContain(@RequestParam String firstName ){
        return employeeService.getByFirstNameContains(firstName);
    }
    
    @PostMapping("/add")
    public Result add(@RequestBody Employee employee){
        return employeeService.add(employee);  
    }


    @GetMapping("/departments")
    public DataResult<List<Department>> getAllDept(){
        return employeeService.getAllDept();
    }

    @GetMapping("/getDept")
    public DataResult<Optional<Department>> getDept(@RequestParam String deptNo){
        return employeeService.getDept(deptNo);
    }

    @GetMapping("/getBaseEmployeeById")
    public DataResult<Optional<BaseEmployee>> getBaseEmployeeById(@RequestParam int id){
        return employeeService.getBaseEmployeeById(id);
    }


    @GetMapping("/getEmployeeSalaries")
    public DataResult<EmployeeSalaries> getEmployeeWithSalariesById(@RequestParam int id){
        return employeeService.getEmployeeWithSalariesById(id);
    }

    @GetMapping("/getEmployeeTitlesByFirstName")
    public DataResult<List<EmployeeTitles>> getEmployeeTitlesByFirstName(@RequestParam String firstName ){
        return employeeService.getEmployeeTitlesByFirstName(firstName);
    }


    @GetMapping("/test")
    public String test(){
        return "connection successful";
    }

}
