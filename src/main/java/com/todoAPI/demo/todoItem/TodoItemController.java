package com.todoAPI.demo.todoItem;

import com.todoAPI.demo.user.User;
import com.todoAPI.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class TodoItemController {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired UserRepository userRepository;

    @GetMapping("/user/{userId}/todoItem/all")
    public @ResponseBody Iterable<TodoItem> getAllTodoItemsForUser(@PathVariable Integer userId){
        return todoItemRepository.findByUserId(userId);
    }

    @PostMapping("/user/{userId}/todoItem/add")
    public @ResponseBody String createTodoItemForUser(@PathVariable Integer userId, @RequestParam String message){
        TodoItem todoItem = new TodoItem();
        todoItem.setMessage(message);
        Optional<User> user = userRepository.findById(userId);
        System.out.printf(String.valueOf(user.get()));
        if(user.isEmpty()){
            return String.valueOf(userId);
        }
        todoItem.setUser(user.get());
        todoItemRepository.save(todoItem);
        return "Saved";
    }
}
