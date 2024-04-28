package com.jetpacker06.bd1.db;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import org.bson.Document;

public class DBAccessor {
    public static int incrementAndGetCYBIG() {
        String counterName = "CYBIGCounter";
        Document doc = DBClient.mongoClient.getDatabase("Misc").getCollection("SaveData").findOneAndUpdate(
                Filters.exists(counterName),
                Updates.inc(counterName, 1),
                new FindOneAndUpdateOptions().upsert(true).returnDocument(ReturnDocument.AFTER)
        );
        assert doc != null;
        return doc.getInteger(counterName);
    }
}
