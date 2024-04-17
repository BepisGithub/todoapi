package com.todoAPI.demo.user;

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

    @Getter
    @Setter
    @OneToMany()
    private List<TodoItem> itemList;

}
