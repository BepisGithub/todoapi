package com.todoAPI.demo.todoItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoItemRepository extends JpaRepository<TodoItem, Integer> {

    Iterable<TodoItem> findByUserId(@Param("userId") Integer userId);
}
