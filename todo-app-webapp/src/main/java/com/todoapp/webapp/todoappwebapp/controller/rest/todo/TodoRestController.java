package com.todoapp.webapp.todoappwebapp.controller.rest.todo;

import com.todoapp.services.todo.todoservices.model.todo.Todo;
import com.todoapp.webapp.todoappwebapp.client.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoRestController.class);

    private final TodoService todoService;

    @Autowired
    public TodoRestController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getTodos() {
        LOGGER.info("Getting all Todos!");

        return todoService.getAllTodos();
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<Object> getTodo(@PathVariable String todoId) {
        LOGGER.info("Getting Todo from the database!");

        return todoService.getTodo(todoId);
    }

    @PostMapping("/todos")
    public ResponseEntity<Object> saveTodo(@RequestBody Todo todo) {
        LOGGER.info("Saving Todo into the database: {} !", todo);

        return todoService.saveTodo(todo);
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<Object> updateTodo(@PathVariable String todoId, @RequestBody Todo todo) {
        LOGGER.info("Updating Todo to {} !", todo);

        return todoService.updateTodo(todoId, todo);
    }

    @DeleteMapping("/todos/{todoId}")
    public ResponseEntity<Object> deleteTodo(@PathVariable String todoId) {
        LOGGER.info("Deleting Todo from the database!");

        return todoService.deleteTodo(todoId);
    }
}
