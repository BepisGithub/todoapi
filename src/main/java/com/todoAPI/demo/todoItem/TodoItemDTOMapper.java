package com.todoAPI.demo.todoItem;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TodoItemDTOMapper {
    public static TodoItemDTO map(TodoItem todoItem){
        TodoItemDTO todoItemDTO = new TodoItemDTO();
        todoItemDTO.setId(todoItem.getId());
        todoItemDTO.setMessage(todoItem.getMessage());
        todoItemDTO.setUserId(todoItem.getUser().getId());
        return todoItemDTO;
    }
}
