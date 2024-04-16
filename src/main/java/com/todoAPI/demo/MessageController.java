package com.todoAPI.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MessageController {
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/message")
    public Message message(@RequestParam(value = "name", defaultValue = "world") String name) {
        return new Message(counter.incrementAndGet(), "Hello, " + name);
    }
}
