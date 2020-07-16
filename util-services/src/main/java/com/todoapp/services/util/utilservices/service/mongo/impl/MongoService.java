package com.todoapp.services.util.utilservices.service.mongo.impl;

import com.todoapp.services.util.utilservices.service.mongo.IMongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.Index;
import org.springframework.stereotype.Service;

@Service
public class MongoService implements IMongoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoService.class);

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public void createIndex(String loggerMessage, String collectionName, String indexKey, String indexName) {
        LOGGER.info(loggerMessage);

        mongoTemplate
                .indexOps(collectionName)
                .ensureIndex(
                        new Index()
                                .on(indexKey, Sort.DEFAULT_DIRECTION)
                                .named(indexName)
                                .unique()
                );
    }
}
