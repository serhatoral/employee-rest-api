package dev.serhat.employeeapi.models;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "titles")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","employees"})
public class Title {

    @Id
    @Column(name = "title")
    private String title;

    @Column(name = "from_date")
    private Date fromDate;
    @Column(name = "to_date")
    private Date toDate;

    @ManyToOne 
    @JoinColumn(name = "emp_no")
    private Employee employees;
}
