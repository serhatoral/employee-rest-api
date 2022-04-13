package dev.serhat.employeeapi.core.utilities.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
    
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
