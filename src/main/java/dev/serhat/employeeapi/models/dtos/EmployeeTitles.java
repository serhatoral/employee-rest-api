package dev.serhat.employeeapi.models.dtos;

import java.util.Collection;

import dev.serhat.employeeapi.models.Title;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTitles {

    private int id;
    private String firstName;
    private String lastName;
    private Collection<Title> titles;
    
}
