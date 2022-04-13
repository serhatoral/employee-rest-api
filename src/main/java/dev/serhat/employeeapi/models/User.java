package dev.serhat.employeeapi.models;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "first_name")
    @Size(min = 3, max = 40, message = "you must enter more than three and less than forty characters")
    @NotBlank(message = "name cannot be blank!")
    @NotNull
    private String firsName;

    @Column(name = "last_name")
    @Size(min = 3, max = 40)
    private String lastName;

    @Column(name = "email")
    @Email(message = "enter your mail!")
    @NotBlank
    @NotNull
    private String email;

    @Column(name = "password")
    @NotBlank(message = "password vannot be blank!")
    @NotNull
    @Size(min = 5, message = "you must enter more five characters!")
    private String password;

    @ManyToMany
    @JoinTable(
        name = "users_roles",
        joinColumns = @JoinColumn(name= "user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id")
    )
    private List<Role> roles;

}
