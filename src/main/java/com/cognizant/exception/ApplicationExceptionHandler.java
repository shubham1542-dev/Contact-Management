package com.cognizant.exception;



import java.util.HashMap;
import java.util.Map;



import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;



@RestControllerAdvice
public class ApplicationExceptionHandler {
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArguments(MethodArgumentNotValidException ex){
        Map<String, String> errorMap = new HashMap<>();
        
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
    return errorMap;
    
}
    
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String,String> handleBusinessException(UserNotFoundException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", ex.getMessage());
        return errorMap;
    }
    
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataAlreayExistException.class)
    public Map<String,String> handleUserAlreadyExistException(DataAlreayExistException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", ex.getMessage());
        return errorMap;
    }
    
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Map<String,String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", ex.getMessage());
        return errorMap;
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Map<String,String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex){
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", ex.getMessage());
        return errorMap;
    }
    
    
    
}