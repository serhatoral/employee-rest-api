package dev.serhat.employeeapi.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptManagerKey implements Serializable {
    
    @Column(name = "dept_no")
    private String deptNo;
    @Column(name = "emp_no")
    private int empNo; 
}
