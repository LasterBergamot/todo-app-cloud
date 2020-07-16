package com.todoapp.services.todo.todoservices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.todoapp.services.todo.todoservices.util.Constants.REQUEST_MAPPING_CREATE_INDEX;
import static com.todoapp.services.todo.todoservices.util.Constants.SERVICE_NAME_UTIL_SERVICES;

@FeignClient(value = SERVICE_NAME_UTIL_SERVICES)
public interface UtilService {

    @PostMapping(REQUEST_MAPPING_CREATE_INDEX)
    ResponseEntity<String> createIndex(@RequestParam String loggerMessage, @RequestParam String collectionName, @RequestParam String indexKey, @RequestParam String indexName);
}
