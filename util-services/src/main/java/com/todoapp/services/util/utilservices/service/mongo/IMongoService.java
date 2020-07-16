package com.todoapp.services.util.utilservices.service.mongo;

public interface IMongoService {
    void createIndex(String loggerMessage, String collectionName, String indexKey, String indexName);
}
