package com.explorecode.course_management.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CourseNotFoundException.class)
    protected ResponseEntity<CustomException> handleCourseNotFoundException(CourseNotFoundException exception, WebRequest webRequest) {
        CustomException error = new CustomException(getTime(), HttpStatus.NOT_FOUND.value(), exception.getMessage(), null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
            
                Map<String, String> errors = new HashMap<>();
                BindingResult br = ex.getBindingResult();
                br.getFieldErrors().forEach(fieldError -> errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
                br.getGlobalErrors().forEach(objError -> errors.put(objError.getObjectName(), objError.getDefaultMessage()));
               
                CustomException exception = new CustomException(getTime(), HttpStatus.BAD_REQUEST.value(), "Invalid Data", errors);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);   
    }

    

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
                Map<String, Object> body = new LinkedHashMap<>();
                body.put("timeStamp", LocalDateTime.now());
                body.put("statusCode", status.value());
                body.put("error", "Bad Request");
                body.put("message", "Invalid date format. Date should be in yyyy-MM-dd format");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class) 
    protected ResponseEntity<CustomException> handleDataIntegrityVoilationException(DataIntegrityViolationException ex, WebRequest request) {
        
        CustomException customException = new CustomException(getTime(), HttpStatus.BAD_REQUEST.value(),ex.getMostSpecificCause().toString(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customException);
    }

    private LocalDateTime getTime() {
        return LocalDateTime.now();
    }

}
