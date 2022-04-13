package dev.serhat.employeeapi.models.dtos;



import java.util.Date;
import java.util.List;

import dev.serhat.employeeapi.models.Salary;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeSalaries {
    
    private  int id;
    private Date birthDate;
    private String firstName;
    private String lastName;
    private char gender;
    private Date hireDate;
    private List<Salary> salaries;

}
