package dev.serhat.employeeapi.models;

import java.sql.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "employees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_no")
    private  int id;

    @Column(name = "birth_date")   
    private Date birthDate;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private char gender;

    @Column(name = "hire_date")  
    private Date hireDate;

    @OneToMany(mappedBy = "employees")
    private List<Title> titles; 

    @OneToMany(mappedBy = "employees")
    private List<Salary> salaryList;

    @OneToMany(mappedBy = "employee")
    private List<DeptEmp> departmentList;

   @OneToMany(mappedBy = "employee")
   private List<DeptManager> managerDepartment;

}
