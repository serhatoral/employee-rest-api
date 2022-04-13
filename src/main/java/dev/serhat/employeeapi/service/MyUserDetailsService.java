package dev.serhat.employeeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import dev.serhat.employeeapi.models.User;
import dev.serhat.employeeapi.repository.UserRepository;
import dev.serhat.employeeapi.security.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        
        User user = userRepository.getByEmail(email);
        if (user==null){

            User emptyUser = new User();
            emptyUser.setEmail(" ");
            emptyUser.setPassword(" ");
            return new MyUserDetails(emptyUser);
        }
        return new MyUserDetails(user);
    }
    
}
