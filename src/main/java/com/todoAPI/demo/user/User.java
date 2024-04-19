package com.todoAPI.demo.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.todoAPI.demo.todoItem.TodoItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true ,mappedBy = "user", fetch = FetchType.LAZY)
    private List<TodoItem> itemList;

}
