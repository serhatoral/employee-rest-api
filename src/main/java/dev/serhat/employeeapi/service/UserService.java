package dev.serhat.employeeapi.service;

import dev.serhat.employeeapi.core.utilities.results.DataResult;
import dev.serhat.employeeapi.core.utilities.results.Result;
import dev.serhat.employeeapi.models.User;

public interface UserService {
    
    Result addUser(User user);

    DataResult<User> getUserByEmail(String email);
    
}
