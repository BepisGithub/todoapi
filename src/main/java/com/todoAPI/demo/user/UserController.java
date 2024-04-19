package com.todoAPI.demo.user;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String createNewUser(@RequestParam String name) {
        User newUser = new User();
        newUser.setName(name);
        userRepository.save(newUser);
        return "saved";
    }

    @GetMapping(path="/users")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();

    }

    @GetMapping(path = "/{userId}")
    public @ResponseBody User getUser(@PathVariable Integer userId){
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @PutMapping(path = "/{userId}/update")
    public @ResponseBody User updateUser(@PathVariable Integer userId, @RequestParam String name){
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }

        User newUser = user.get();

        newUser.setName(name);

        userRepository.save(newUser);

        if(userRepository.findById(userId).get().getName().equals(name)){
            return newUser;
        }
        throw new RuntimeException("Error");

    }

    @DeleteMapping(path = "/{userId}/delete")
    public @ResponseBody String deleteUserById(@PathVariable Integer userId){
        userRepository.deleteById(userId);
        if(userRepository.existsById(userId)){
            return "Failed";
        }
        return "Success";
    }
}
