package dev.serhat.employeeapi.models;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "dept_emp")
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","employee"})
public class DeptEmp{
    
   @EmbeddedId
   @JsonIgnore
   private EmpDeptKey id;

   
    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "emp_no")
    private Employee employee;

    
    @ManyToOne
    @MapsId("departmentNo")
    @JoinColumn(name = "dept_no")
    private Department department;

    @Column(name = "from_date")
    public Date fromDate;

    @Column(name = "to_date")
    public Date toDate;

}
