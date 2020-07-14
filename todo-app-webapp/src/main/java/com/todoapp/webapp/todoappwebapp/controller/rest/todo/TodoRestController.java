package com.todoapp.webapp.todoappwebapp.controller.rest.todo;

import com.todoapp.services.todo.todoservices.model.todo.Todo;
import com.todoapp.webapp.todoappwebapp.client.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
