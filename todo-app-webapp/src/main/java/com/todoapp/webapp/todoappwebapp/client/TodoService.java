package com.todoapp.webapp.todoappwebapp.client;

import com.todoapp.services.todo.todoservices.model.todo.Todo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "todo-services") // the name of the service in Eureka
public interface TodoService {

    @GetMapping("/todos")
    ResponseEntity<List<Todo>> getAllTodos();

    @GetMapping("/todos/{todoId}")
    ResponseEntity<Object> getTodo(@PathVariable String todoId);

    @PostMapping("/todos")
    ResponseEntity<Object> saveTodo(@RequestBody Todo todo);

    @PutMapping("/todos/{todoId}")
    ResponseEntity<Object> updateTodo(@PathVariable String todoId, @RequestBody Todo todo);

    @DeleteMapping("/todos/{todoId}")
    ResponseEntity<Object> deleteTodo(@PathVariable String todoId);
}
