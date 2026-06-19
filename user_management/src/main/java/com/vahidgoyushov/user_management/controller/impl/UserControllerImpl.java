package com.vahidgoyushov.user_management.controller.impl;

import com.vahidgoyushov.user_management.controller.IUserController;
import com.vahidgoyushov.user_management.dto.LoginRequest;
import com.vahidgoyushov.user_management.dto.SaveRequest;
import com.vahidgoyushov.user_management.entity.User;
import com.vahidgoyushov.user_management.exception.VahidException;
import com.vahidgoyushov.user_management.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path="/rest/api/user")
public  class UserControllerImpl implements IUserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> saveUser(@Valid @RequestBody SaveRequest req) throws VahidException {
        User user = userService.saveUser(req.getUsername(),
                req.getPassword(),
                req.getRoles());
        return ResponseEntity.ok("User created: " + user.getUsername()+" "+user.getId());
    }

    @GetMapping(path="/list")
    @Override
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path="/list/{id}")
    @Override
    public User getUserById(@PathVariable(name="id") Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping(path="/delete/{id}")
    @Override
    public String deleteUser(@PathVariable(name="id")Long id) {
        User user =userService.getUserById(id);
        String deletedUserName = user.getUsername();
        userService.deleteUser(id);
        return "User deleted: "+deletedUserName;
    }

    @PutMapping(path="/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable(name="id")Long id,@Valid @RequestBody User updateUser) throws VahidException {
        User updated = userService.updateUser(id, updateUser);
        return ResponseEntity.ok("User updated: " + updated.getUsername());
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginUser( @Valid @RequestBody LoginRequest req) {
        User user = userService.loginUser(req.getUsername(), req.getPassword());
        return ResponseEntity.ok("Usere login olundu: " + req.getUsername());
    }

}

