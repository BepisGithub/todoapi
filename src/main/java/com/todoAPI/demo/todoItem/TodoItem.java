package com.todoAPI.demo.todoItem;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.todoAPI.demo.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
public class TodoItem {
    @Id
    @GeneratedValue
    private Integer id;

    @Getter
    @Setter
    private String message;

    @JsonBackReference
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
