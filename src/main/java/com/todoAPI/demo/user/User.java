package com.todoAPI.demo.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue
    private Integer Id;

    @Getter
    @Setter
    private String name;

}
