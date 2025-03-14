package com.navneet.openai.utils;

import com.navneet.openai.mongo.model.ContextData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public class ContextUtils {

    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * Retrieves a ContextData object from the MongoDB collection based on the provided name.
     *
     * @param name the name of the context data to retrieve
     * @return the ContextData object with the specified name, or null if not found
     */
    public ContextData getContextData(String name){
        return mongoTemplate.findOne(new Query(Criteria.where("name").is(name)), ContextData.class);
    }

    /**
     * Saves the provided ContextData object to the MongoDB collection.
     *
     * @param contextData the ContextData object to save
     * @return the saved ContextData object
     */
    public ContextData saveContextData(ContextData contextData) {
        return mongoTemplate.save(contextData);
    }
}
