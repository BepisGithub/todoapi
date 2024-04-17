package com.todoAPI.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
