package com.todoapp.services.todo.todoservices.controller.rest.todo;

import com.todoapp.services.todo.todoservices.model.todo.Todo;
import com.todoapp.services.todo.todoservices.service.ITodoService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoRestController.class);

    private static final String GET_MAPPING_TODOS = "/todos";
    private static final String GET_MAPPING_TODOS_WITH_TODO_ID_PATHVAR = "/todos/{todoId}";

    private static final String POST_MAPPING_TODOS = "/todos";

    private static final String PUT_MAPPING_TODOS_WITH_TODO_ID_PATHVAR = "/todos/{todoId}";

    private static final String DELETE_MAPPING_TODOS_WITH_TODO_ID_PATHVAR = "/todos/{todoId}";

    private final ITodoService todoService;

    @Autowired
    public TodoRestController(ITodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(GET_MAPPING_TODOS)
    public ResponseEntity<List<Todo>> getAllTodos() {
        LOGGER.info("Getting all Todos from the database!");

        return todoService.getTodos();
    }

    @GetMapping(GET_MAPPING_TODOS_WITH_TODO_ID_PATHVAR)
    public ResponseEntity<Object> getTodo(@PathVariable String todoId) {
        LOGGER.info("Getting Todo from the database!");

        return todoService.getTodo(todoId);
    }

    @PostMapping(POST_MAPPING_TODOS)
    public ResponseEntity<Object> saveTodo(@RequestBody Todo todo) {
        LOGGER.info("Saving Todo into the database: {} !", todo);

        return todoService.saveTodo(todo);
    }

    @PutMapping(PUT_MAPPING_TODOS_WITH_TODO_ID_PATHVAR)
    public ResponseEntity<Object> updateTodo(@PathVariable String todoId, @RequestBody Todo todo) {
        LOGGER.info("Updating Todo to {} !", todo);

        return todoService.updateTodo(todoId, todo);
    }

    @DeleteMapping(DELETE_MAPPING_TODOS_WITH_TODO_ID_PATHVAR)
    public ResponseEntity<Object> deleteTodo(@PathVariable String todoId) {
        LOGGER.info("Deleting Todo from the database!");

        return todoService.deleteTodo(todoId);
    }
}