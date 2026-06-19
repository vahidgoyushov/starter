package com.vahidgoyushov.user_management.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaveRequest {

    @NotEmpty(message = "Username bos ola bilmez!")
    private String username;
    private String password;
    private Set<String> roles;

}
