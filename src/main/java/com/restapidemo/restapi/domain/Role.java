package com.restapidemo.restapi.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Collection<User> user;

    public Role(String name) {
        this.name = name;
    }
    public Role() {

    }

}
