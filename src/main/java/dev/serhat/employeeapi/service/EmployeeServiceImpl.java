package dev.serhat.employeeapi.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import dev.serhat.employeeapi.core.utilities.exceptions.EmployeeNotFoundException;
import dev.serhat.employeeapi.core.utilities.results.DataResult;
import dev.serhat.employeeapi.core.utilities.results.Result;
import dev.serhat.employeeapi.core.utilities.results.SuccessDataResult;
import dev.serhat.employeeapi.core.utilities.results.SuccessResult;
import dev.serhat.employeeapi.models.Department;
import dev.serhat.employeeapi.models.Employee;
import dev.serhat.employeeapi.models.dtos.BaseEmployee;
import dev.serhat.employeeapi.models.dtos.EmployeeSalaries;
import dev.serhat.employeeapi.models.dtos.EmployeeTitles;
import dev.serhat.employeeapi.repository.DepartmentRepository;
import dev.serhat.employeeapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository= departmentRepository;
    }



    @Override
    public DataResult<Optional<Employee>> getEmployee(int id) {  
        
        Optional<Employee> opemployee = employeeRepository.findById(id);

       if(opemployee.isPresent()==false){
        throw new EmployeeNotFoundException(id +": User that have this id  wasn't found !");  
       }
          
       return new SuccessDataResult<Optional<Employee>>(opemployee);
       
    }


    @Override
    public DataResult<List<Employee>> getByFirstNameContains(String firstName) {
       
        return new SuccessDataResult<List<Employee>>(employeeRepository.getByFirstNameContains(firstName),"first name ile uyuşan veriler listelendi.");
    }



    @Override
    public Result add(Employee employee) {
        employeeRepository.save(employee);
        return new SuccessResult("Employee was added.");
    }



    @Override
    public DataResult<List<Employee>> getAllEmployees(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return new SuccessDataResult<List<Employee>>
        (employeeRepository.findAll(pageable).getContent());
    }



    @Override
    public DataResult<List<Department>> getAllDept() {
        
        return new SuccessDataResult<List<Department>>(departmentRepository.findAll());
    }



    @Override
    public DataResult<Optional<Department>> getDept(String deptNo) {
        
        return new SuccessDataResult<Optional<Department>>(departmentRepository.findById(deptNo)); 
    }



    @Override
    public DataResult<Optional<BaseEmployee>> getBaseEmployeeById(int id) {
        
        return new SuccessDataResult<Optional<BaseEmployee>>(employeeRepository.getBaseEmployeeById(id));
    }



    @Override
    public DataResult<EmployeeSalaries> getEmployeeWithSalariesById(int id) {
    
        Employee employee = employeeRepository.getById(id);

        EmployeeSalaries employeeSalaries = new EmployeeSalaries(
            employee.getId(), employee.getBirthDate(), employee.getFirstName(),
             employee.getLastName(), employee.getGender(), employee.getHireDate(),employee.getSalaryList()
        );

        return new SuccessDataResult<EmployeeSalaries>(employeeSalaries);
    }



    @Override
    public DataResult<List<EmployeeTitles>> getEmployeeTitlesByFirstName(String firstName) {
        List<Employee> employees = employeeRepository.getByFirstNameStartsWith(firstName);

        List<EmployeeTitles> employeeTitles = employees.stream()
            .map(emp -> new EmployeeTitles(emp.getId(), emp.getFirstName(), emp.getLastName(),
            emp.getTitles()))
            .collect(Collectors.toList());

        return new SuccessDataResult<List<EmployeeTitles>>(employeeTitles);
    }







   


    

  
    

}
