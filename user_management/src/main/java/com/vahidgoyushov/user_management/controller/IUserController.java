package com.vahidgoyushov.user_management.controller;

import com.vahidgoyushov.user_management.dto.LoginRequest;
import com.vahidgoyushov.user_management.dto.SaveRequest;
import com.vahidgoyushov.user_management.entity.User;
import com.vahidgoyushov.user_management.exception.VahidException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserController {
    ResponseEntity<?> saveUser(SaveRequest req) throws VahidException;

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public String deleteUser(Long id);

    ResponseEntity<?> updateUser(Long id , User updateUser) throws VahidException;

    ResponseEntity<?> loginUser(LoginRequest req) ;


}
