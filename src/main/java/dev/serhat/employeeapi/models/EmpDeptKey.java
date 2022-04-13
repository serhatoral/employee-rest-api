package dev.serhat.employeeapi.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class EmpDeptKey implements Serializable {
    
   @Column(name = "emp_no")
   private int employeeId;

   @Column(name = "dept_no")
   private String departmentNo;

}
