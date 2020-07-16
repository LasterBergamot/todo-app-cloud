package com.todoapp.services.user.userservices.client.util;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.todoapp.services.user.userservices.util.Constants.REQUEST_MAPPING_CREATE_INDEX;
import static com.todoapp.services.user.userservices.util.Constants.SERVICE_NAME_UTIL_SERVICES;

@FeignClient(value = SERVICE_NAME_UTIL_SERVICES)
public interface UtilService {

    @PostMapping(REQUEST_MAPPING_CREATE_INDEX)
    ResponseEntity<String> createIndex(@RequestParam String loggerMessage, @RequestParam String collectionName, @RequestParam String indexKey, @RequestParam String indexName);
}
