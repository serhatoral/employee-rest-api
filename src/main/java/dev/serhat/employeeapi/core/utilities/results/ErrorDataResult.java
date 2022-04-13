package dev.serhat.employeeapi.core.utilities.results;

public class ErrorDataResult<T> extends DataResult<T>{

    public ErrorDataResult(T data, String messsage) {
        super(data, false, messsage);
    }

    public ErrorDataResult(T data) {
        super(data, false);
    }
    
}
