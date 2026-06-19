package com.vahidgoyushov.user_management;

import com.vahidgoyushov.user_management.entity.Role;
import com.vahidgoyushov.user_management.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

    @Bean
    CommandLineRunner run(RoleRepository roleRepository) {
        return args -> {
            if(roleRepository.findByName("Admin").isEmpty()) {
                roleRepository.save(new Role("Admin"));
            }
            if(roleRepository.findByName("User").isEmpty()) {
                roleRepository.save(new Role("User"));
            }
        };
    }

}
