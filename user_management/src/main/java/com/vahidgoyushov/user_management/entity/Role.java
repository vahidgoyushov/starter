package com.vahidgoyushov.user_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name="roles",schema="public")
@Getter
@Setter

public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name",unique = true, nullable = false)
    private String name;

    @ManyToMany(mappedBy="roles")
    @JsonIgnore
    private Set<User> users= new HashSet<>();


    public Role(String name) {
        this.name = name;
    }


    public Role() {

    }
}
