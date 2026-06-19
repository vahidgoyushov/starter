package com.vahidgoyushov.user_management.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private List<String> addMapValue(List<String> list, String newValue){
        list.add(newValue);
        return list;
    }

    @ExceptionHandler(value= MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        Map<String, List<String>> errorsMap=new HashMap<>();

        for(ObjectError objError:e.getBindingResult().getAllErrors()){
            String fieldName=((FieldError)objError).getField();
            if(errorsMap.containsKey(fieldName)){
                errorsMap.put(fieldName,addMapValue(errorsMap.get(fieldName),objError.getDefaultMessage()));
            }else{
                errorsMap.put(fieldName, addMapValue(new ArrayList<>(), objError.getDefaultMessage()));
            }
        }
        return ResponseEntity.badRequest().body(createApiError(errorsMap));

    }

    @ExceptionHandler(VahidException.class)
    public ResponseEntity<ApiError> handleVahidException(VahidException e){
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("password", Collections.singletonList(e.getMessage()));

        return ResponseEntity.badRequest().body(createApiError(errors));
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException e){
        Map<String, List<String>> errors = new HashMap<>();
        errors.put("error", Collections.singletonList(e.getMessage()));

        return ResponseEntity.badRequest().body(createApiError(errors));
    }


    private <T> ApiError<T> createApiError(T errors){
        ApiError<T> apiError = new ApiError<T>();
        apiError.setId(UUID.randomUUID().toString());
        apiError.setErrorTime(new Date());
        apiError.setErrors(errors);

        return apiError;
    }

}
