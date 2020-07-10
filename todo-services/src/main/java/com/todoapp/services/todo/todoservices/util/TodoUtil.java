package com.todoapp.services.todo.todoservices.util;

import com.todoapp.services.todo.todoservices.model.todo.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TodoUtil {

    private TodoUtil() {}

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoUtil.class);

    public static Todo updateTodo(Todo todoFromRepo, Todo todoFromJSON) {
        LOGGER.info("Updating Todo!");
        LOGGER.info("Original: {}", todoFromRepo);

        todoFromRepo.setName(todoFromJSON.getName());
        todoFromRepo.setDeadline(todoFromJSON.getDeadline());
        todoFromRepo.setPriority(todoFromJSON.getPriority());

        LOGGER.info("Updated: {}", todoFromRepo);

        return todoFromRepo;
    }
}
