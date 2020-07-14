package com.todoapp.webapp.todoappwebapp.client;

import com.todoapp.services.todo.todoservices.model.todo.Todo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "todo-services") // the name of the service in Eureka
public interface TodoService {

    @GetMapping("/todos")
    ResponseEntity<List<Todo>> getAllTodos();
}
