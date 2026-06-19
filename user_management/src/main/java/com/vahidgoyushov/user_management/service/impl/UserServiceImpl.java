package com.vahidgoyushov.user_management.service.impl;

import com.vahidgoyushov.user_management.entity.Role;
import com.vahidgoyushov.user_management.entity.User;
import com.vahidgoyushov.user_management.exception.VahidException;
import com.vahidgoyushov.user_management.repository.RoleRepository;
import com.vahidgoyushov.user_management.repository.UserRepository;
import com.vahidgoyushov.user_management.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.vahidgoyushov.user_management.exception.Vahid.validate;


@Service
public  class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveUser(String username, String password, Set<String> roleNames) throws VahidException {
        validate(password);

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setEnabled(true);

        for (String name : roleNames) {
            Role role = roleRepository.findByName(name)
                    .orElseThrow(() -> new RuntimeException("Role tapılmadı: " + name));

            user.getRoles().add((role));
        }

        return userRepository.save(user);
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User tapılmadı ID: " + id));
    }


    @Override
    public void deleteUser(Long id) {
        User dbUser = getUserById(id);
        if (dbUser != null) {
            userRepository.delete(dbUser);
        }
    }

    @Override
    public User updateUser(Long id, User updateUser) throws VahidException{
        User dbUser = getUserById(id);
        dbUser.setUsername(updateUser.getUsername());
        if (updateUser.getPassword() != null && !updateUser.getPassword().isEmpty()) {
            validate(updateUser.getPassword());
            dbUser.setPassword(passwordEncoder.encode(updateUser.getPassword()));

        }
            return userRepository.save(dbUser);
        }

       @Override
        public User loginUser(String username, String password) {
        User user=userRepository.findByUsername(username)
                .orElseThrow(()-> new RuntimeException("Bele username tapilmadi!"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Password yanlisdir!");

        }
           return user;
        }




}


