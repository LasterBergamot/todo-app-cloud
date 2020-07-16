package com.todoapp.webapp.todoappwebapp.controller.rest.todo;

import com.todoapp.services.todo.todoservices.model.todo.Todo;
import com.todoapp.webapp.todoappwebapp.client.todo.TodoService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.BackendId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static com.todoapp.webapp.todoappwebapp.util.Constants.REQUEST_MAPPING_TODOS;
import static com.todoapp.webapp.todoappwebapp.util.Constants.REQUEST_MAPPING_TODOS_WITH_TODO_ID_PATHVAR;

@RestController
public class TodoRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoRestController.class);

    private final TodoService todoService;

    @Autowired
    public TodoRestController(TodoService todoService) {
        this.todoService = todoService;
    }

    @CircuitBreaker(name = "backendA", fallbackMethod = "getTodosFallback")
    @GetMapping(REQUEST_MAPPING_TODOS)
    public ResponseEntity<List<Todo>> getTodos() {
        LOGGER.info("Getting all Todos!");

        return todoService.getAllTodos();
    }

    public ResponseEntity<List<Todo>> getTodosFallback(Exception exception) {
        LOGGER.info("Circuit breaker was called when trying to get all Todos!");
        LOGGER.info("Exception: {} - Cause: {} - Message: {}", exception.getClass().getSimpleName(), exception.getCause(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.emptyList());
    }
    
    @GetMapping(REQUEST_MAPPING_TODOS_WITH_TODO_ID_PATHVAR)
    public ResponseEntity<Object> getTodo(@PathVariable String todoId) {
        LOGGER.info("Getting Todo from the database with id: {}!", todoId);

        return todoService.getTodo(todoId);
    }

    @PostMapping(REQUEST_MAPPING_TODOS)
    public ResponseEntity<Object> saveTodo(@RequestBody Todo todo) {
        LOGGER.info("Saving Todo into the database: {} !", todo);

        return todoService.saveTodo(todo);
    }

    @PutMapping(REQUEST_MAPPING_TODOS_WITH_TODO_ID_PATHVAR)
    public ResponseEntity<Object> updateTodo(@PathVariable String todoId, @RequestBody Todo todo) {
        LOGGER.info("Updating Todo to {} !", todo);

        return todoService.updateTodo(todoId, todo);
    }

    @DeleteMapping(REQUEST_MAPPING_TODOS_WITH_TODO_ID_PATHVAR)
    public ResponseEntity<Object> deleteTodo(@PathVariable String todoId) {
        LOGGER.info("Deleting Todo from the database!");

        return todoService.deleteTodo(todoId);
    }
}
