package com.todoAPI.demo.todoItem;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodoItemDTO {

    private Integer id;

    private String message;

    private Integer userId;
}
