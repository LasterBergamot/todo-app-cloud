package com.todoapp.services.todo.todoservices.service.impl;

import com.todoapp.services.todo.todoservices.client.UtilService;
import com.todoapp.services.todo.todoservices.model.todo.Todo;
import com.todoapp.services.todo.todoservices.repository.todo.TodoRepository;
import com.todoapp.services.todo.todoservices.service.ITodoService;
import com.todoapp.services.todo.todoservices.util.TodoUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.todoapp.services.todo.todoservices.util.Constants.COLLECTION_NAME_TODO;
import static com.todoapp.services.todo.todoservices.util.Constants.ERR_MSG_NO_TODO_WAS_FOUND_WITH_THE_GIVEN_ID;
import static com.todoapp.services.todo.todoservices.util.Constants.ERR_MSG_NULL_JSON;
import static com.todoapp.services.todo.todoservices.util.Constants.ERR_MSG_NULL_OR_EMPTY_ID;
import static com.todoapp.services.todo.todoservices.util.Constants.INDEX_NAME_TODO_NAME_INDEX;
import static com.todoapp.services.todo.todoservices.util.Constants.KEY_NAME;

@Service
@Validated
public class TodoService implements ITodoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoService.class);

    private final TodoRepository todoRepository;
    private final UtilService utilService;

    @Autowired
    public TodoService(TodoRepository todoRepository, UtilService utilService) {
        this.todoRepository = todoRepository;
        this.utilService = utilService;
    }

    @PostConstruct
    public void initIndexes() {
        utilService.createIndex("Creating index for the 'name' field of Todo.", COLLECTION_NAME_TODO, KEY_NAME, INDEX_NAME_TODO_NAME_INDEX);
    }

    /**
     * Return with all of the Todos found in the database.
     *
     * @return - a ResponseEntity with HttpStatus.OK (200) and all of the Todos from the database, with all of their fields.
     */
    @Override
    public ResponseEntity<List<Todo>> getTodos() {
        LOGGER.info("Getting all Todos from the database!");

        return ResponseEntity.ok(todoRepository.findAll());
    }

    /**
     * Returns a ResponseEntity with the TodoObject if any exists with the given ID.
     *
     * @param todoId - the ID of the desired TodoObject
     * @return - ResponseEntity with HttpStatus.OK (200) and the TodoObject if it exists,
     * else a ResponseEntity with HttpStatus.NOT_FOUND (404)
     */
    @Override
    public ResponseEntity<Object> getTodo(String todoId) {
        if (ObjectUtils.isEmpty(todoId)) {
            return getErrorSpecificResponseEntity(HttpStatus.BAD_REQUEST, ERR_MSG_NULL_OR_EMPTY_ID);
        }

        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        ResponseEntity<Object> responseEntity;

        if (optionalTodo.isPresent()) {
            LOGGER.info("Getting Todo from the database!");

            responseEntity = ResponseEntity.ok(optionalTodo.get());
        } else {
            responseEntity = getErrorSpecificResponseEntity(HttpStatus.NOT_FOUND, ERR_MSG_NO_TODO_WAS_FOUND_WITH_THE_GIVEN_ID);
        }

        return responseEntity;
    }

    /**
     * Saves the given TodoObject into the database, if it's not null and it's valid.
     *
     * @param todoFromJSON - a valid TodoObject in JSON format
     * @return - a ResponseEntity with HttpStatus.CREATED (201) and with the saved TodoObject,
     * else a ResponseEntity with HttpStatus.BAD_REQUEST (400)
     */
    @Override
    public ResponseEntity<Object> saveTodo(@Valid Todo todoFromJSON) {
        if (ObjectUtils.isEmpty(todoFromJSON)) {
            return getErrorSpecificResponseEntity(HttpStatus.BAD_REQUEST, ERR_MSG_NULL_JSON);
        }

        LOGGER.info("Saving Todo into the database!");

        return ResponseEntity.status(HttpStatus.CREATED).body(todoRepository.save(todoFromJSON));
    }

    /**
     * Updates the TodoObject, given by its ID, with the given TodoObject, if it's valid.
     *
     * @param todoId       - the desired TodoObject to be updated
     * @param todoFromJSON - the TodoObject used to update the already existing TodoObject
     * @return - a ResponseEntity with HttpStatus.BAD_REQUEST (400) if the given ID- or the given TodoFromJSON object is null,
     * a ResponseEntity with HttpStatus.NOT_FOUND (404) if with the given ID no TodoObject was found,
     * else a ResponseEntity with HttpStatus.OK (200) with the updated TodoObject
     */
    @Override
    public ResponseEntity<Object> updateTodo(String todoId, @Valid Todo todoFromJSON) {
        if (ObjectUtils.isEmpty(todoId)) {
            return getErrorSpecificResponseEntity(HttpStatus.BAD_REQUEST, ERR_MSG_NULL_OR_EMPTY_ID);
        } else if (ObjectUtils.isEmpty(todoFromJSON)) {
            return getErrorSpecificResponseEntity(HttpStatus.BAD_REQUEST, ERR_MSG_NULL_JSON);
        }

        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        ResponseEntity<Object> responseEntity;

        if (optionalTodo.isPresent()) {
            Todo updatedTodo = TodoUtil.updateTodo(optionalTodo.get(), todoFromJSON);

            responseEntity = ResponseEntity.status(HttpStatus.OK).body(todoRepository.save(updatedTodo));
        } else {
            responseEntity = getErrorSpecificResponseEntity(HttpStatus.NOT_FOUND, ERR_MSG_NO_TODO_WAS_FOUND_WITH_THE_GIVEN_ID);
        }

        return responseEntity;
    }

    /**
     * Deletes the TodoObject from the database with the given ID.
     *
     * @param todoId - the ID of the TodoObject to be deleted
     * @return - a ResponseEntity with HttpStatus.BAD_REQUEST (400), if the given ID was null,
     * a ResponseEntity with HttpStatus.NOT_FOUND (404), if no TodoObject was found with the given ID,
     * else a ResponseEntity with HttpStatus.OK (200), if the TodoObject was successfully deleted.
     */
    @Override
    public ResponseEntity<Object> deleteTodo(String todoId) {
        if (ObjectUtils.isEmpty(todoId)) {
            return getErrorSpecificResponseEntity(HttpStatus.BAD_REQUEST, ERR_MSG_NULL_OR_EMPTY_ID);
        }

        Optional<Todo> optionalTodo = todoRepository.findById(todoId);
        ResponseEntity<Object> responseEntity;

        if (optionalTodo.isPresent()) {
            LOGGER.info("Deleting Todo from the database!");

            todoRepository.deleteById(todoId);
            responseEntity = new ResponseEntity<>(HttpStatus.OK);
        } else {
            responseEntity = getErrorSpecificResponseEntity(HttpStatus.NOT_FOUND, ERR_MSG_NO_TODO_WAS_FOUND_WITH_THE_GIVEN_ID);
        }

        return responseEntity;
    }

    private ResponseEntity<Object> getErrorSpecificResponseEntity(HttpStatus httpStatus, String errorMessage) {
        LOGGER.error(errorMessage);

        return ResponseEntity.status(httpStatus).body(errorMessage);
    }
}