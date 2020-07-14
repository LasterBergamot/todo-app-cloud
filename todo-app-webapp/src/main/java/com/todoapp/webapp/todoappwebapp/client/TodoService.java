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

import static com.todoapp.webapp.todoappwebapp.util.Constants.REQUEST_MAPPING_TODOS;
import static com.todoapp.webapp.todoappwebapp.util.Constants.REQUEST_MAPPING_TODOS_WITH_TODO_ID_PATHVAR;
import static com.todoapp.webapp.todoappwebapp.util.Constants.SERVICE_NAME_TODO_SERVICES;

@FeignClient(value = SERVICE_NAME_TODO_SERVICES) // the name of the service in Eureka
public interface TodoService {

    @GetMapping(REQUEST_MAPPING_TODOS)
    ResponseEntity<List<Todo>> getAllTodos();

    @GetMapping(REQUEST_MAPPING_TODOS_WITH_TODO_ID_PATHVAR)
    ResponseEntity<Object> getTodo(@PathVariable String todoId);

    @PostMapping(REQUEST_MAPPING_TODOS)
    ResponseEntity<Object> saveTodo(@RequestBody Todo todo);

    @PutMapping(REQUEST_MAPPING_TODOS_WITH_TODO_ID_PATHVAR)
    ResponseEntity<Object> updateTodo(@PathVariable String todoId, @RequestBody Todo todo);

    @DeleteMapping(REQUEST_MAPPING_TODOS_WITH_TODO_ID_PATHVAR)
    ResponseEntity<Object> deleteTodo(@PathVariable String todoId);
}
