package com.vahidgoyushov.user_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vahidgoyushov.user_management.exception.VahidException;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users",schema="public")
@Getter
@Setter

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="user_name",unique = true, nullable = false)
    @NotEmpty(message = "Username yeri bos qala bilmez!")
    private String username;
    private String password;
    @Column(nullable = false)
    private boolean enabled=true;
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name="role_id")
    )
    @JsonIgnoreProperties("users")
    private Set<Role> roles=new HashSet<>();


}
