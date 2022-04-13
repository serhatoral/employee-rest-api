package dev.serhat.employeeapi.core.utilities.exceptions;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import dev.serhat.employeeapi.core.utilities.results.ErrorDataResult;
import dev.serhat.employeeapi.core.utilities.results.ErrorResult;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });         
        
        ErrorDataResult<Object> errorDataResult = new ErrorDataResult<Object>(errors,"Doğrulama hataları!");

        return new ResponseEntity<Object>(errorDataResult,HttpStatus.BAD_REQUEST);
    }
    

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> employeeNotFoundException(EmployeeNotFoundException employeeNotFoundException){

        ErrorResult errorResult = new ErrorResult(employeeNotFoundException.getMessage());
        System.out.print(employeeNotFoundException.getMessage());
        return new ResponseEntity<Object>(errorResult, HttpStatus.NOT_FOUND);
    }

    
    

}
