package dev.serhat.employeeapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import dev.serhat.employeeapi.models.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    
    User getByEmail(String email);
}
