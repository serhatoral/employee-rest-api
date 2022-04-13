package dev.serhat.employeeapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.serhat.employeeapi.core.utilities.results.DataResult;
import dev.serhat.employeeapi.core.utilities.results.Result;
import dev.serhat.employeeapi.core.utilities.results.SuccessDataResult;
import dev.serhat.employeeapi.core.utilities.results.SuccessResult;
import dev.serhat.employeeapi.models.User;
import dev.serhat.employeeapi.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
    
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public Result addUser(User user) {
        
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return new SuccessResult("User's been added.");
        
    }

    @Override
    public DataResult<User> getUserByEmail(String email) {
        return new SuccessDataResult<User>(userRepository.getByEmail(email));
    }




    
    
}
