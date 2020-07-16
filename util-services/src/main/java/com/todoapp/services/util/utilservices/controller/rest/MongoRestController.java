package com.todoapp.services.util.utilservices.controller.rest;

import com.todoapp.services.util.utilservices.service.mongo.IMongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.todoapp.services.util.utilservices.util.Constants.REQUEST_MAPPING_CREATE_INDEX;

@RestController
public class MongoRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoRestController.class);

    private final IMongoService mongoService;

    @Autowired
    public MongoRestController(IMongoService mongoService) {
        this.mongoService = mongoService;
    }

    @PostMapping(REQUEST_MAPPING_CREATE_INDEX)
    public ResponseEntity<String> createIndex(@RequestParam String loggerMessage, @RequestParam String collectionName, @RequestParam String indexKey, @RequestParam String indexName) {
        LOGGER.info("Creating index with key [{}] and name [{}] for collection [{}]!", indexKey, indexName, collectionName);

        String successMessage = String.format("Index with key [%s] and name [%s] was successfully created for collection [%s]!", indexKey, indexName, collectionName);

        mongoService.createIndex(loggerMessage, collectionName, indexKey, indexName);

        return ResponseEntity.ok(successMessage);
    }
}
