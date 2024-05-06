package com.todoAPI.demo.todoItem;

import com.todoAPI.demo.user.User;
import com.todoAPI.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class TodoItemController {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Autowired UserRepository userRepository;

    @PostMapping(path="/user/{userId}/todoItem/add")
    public @ResponseBody String createTodoItemForUser(@PathVariable Integer userId, @RequestParam String message){
        TodoItem todoItem = new TodoItem();
        todoItem.setMessage(message);
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return "Errorz";
        }
        todoItem.setUser(user.get());
        todoItemRepository.save(todoItem);
        userRepository.save(user.get());
        return "Saved";
    }

    @GetMapping("/user/{userId}/todoItem/all")
    public @ResponseBody Iterable<TodoItem> getAllTodoItemsForUser(@PathVariable Integer userId){
        return todoItemRepository.findByUserId(userId);
    }

    @GetMapping("/user/{userId}/todoItem/{todoId}")
    public @ResponseBody TodoItemDTO getTodoItemForUser(@PathVariable Integer userId, @PathVariable Integer todoId){
        Optional<TodoItem> item = todoItemRepository.findById(todoId);
        if(item.isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "no todo item");
        }
        if(!Objects.equals(item.get().getUser().getId(), userId)){
            throw new ResponseStatusException(NOT_FOUND, "no todo item associate with user");
        }
        return TodoItemDTOMapper.map(item.get());
    }

    @PutMapping("/user/{userId}/todoItem/{todoId}/update")
    public @ResponseBody TodoItemDTO updateTodoItem(@PathVariable Integer userId, @PathVariable Integer todoId,
                                                    @RequestParam String message){
        Optional<TodoItem> todoItem = todoItemRepository.findById(todoId);
        if(todoItem.isEmpty()){
            throw new ResponseStatusException(NOT_FOUND, "no todo item");
        }
        if(!todoItem.get().getUser().getId().equals(userId)){
            throw new ResponseStatusException(FORBIDDEN, "wrong userid");
        }

        todoItem.get().setMessage(message);
        todoItemRepository.save(todoItem.get());

        if(todoItemRepository.findById(todoId).get().getMessage().equals(message)){
            return TodoItemDTOMapper.map(todoItem.get());
        }

        throw new RuntimeException("Error");

    }

    @DeleteMapping(path="/user/{userId}/todoItem/{todoId}/delete")
    public @ResponseBody String deleteUsersTodoItem(@PathVariable Integer userId, @PathVariable Integer todoId){
        todoItemRepository.deleteById(todoId);
        if(todoItemRepository.existsById(todoId)){
            throw new RuntimeException("Error");
        }
        return "Success";
    }

}
