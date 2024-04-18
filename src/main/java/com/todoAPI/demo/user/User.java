package com.todoAPI.demo.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.todoAPI.demo.todoItem.TodoItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Getter
    @Setter
    private String name;

    @JsonManagedReference
    @Getter
    @Setter
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<TodoItem> itemList;

}
