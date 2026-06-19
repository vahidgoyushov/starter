package com.vahidgoyushov.user_management.service;

import com.vahidgoyushov.user_management.entity.User;
import com.vahidgoyushov.user_management.exception.VahidException;

import java.util.List;
import java.util.Set;

public interface IUserService {

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public void deleteUser(Long id);

    public User updateUser(Long id , User updateUser) throws VahidException;

    public User saveUser(String username, String password, Set<String> roles) throws VahidException;

    public User loginUser(String username, String password) ;
}
