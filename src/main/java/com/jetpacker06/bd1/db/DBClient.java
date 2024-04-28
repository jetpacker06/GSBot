package com.jetpacker06.bd1.db;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class DBClient {
    protected static MongoClient mongoClient;

    public static void init() {
        final String password = "sgRor7HG0rCxhF9H";
        String connectionString = "mongodb+srv://jetpacker06:" + password + "@bd1.vpsx9x7.mongodb.net/?retryWrites=true&w=majority&appName=BD1";
        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();
        try {
            mongoClient = MongoClients.create(settings);

            System.out.println("Connected to MongoDB");
        } catch (MongoException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
