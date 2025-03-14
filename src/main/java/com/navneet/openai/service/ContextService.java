package com.navneet.openai.service;

import com.navneet.openai.models.request.ContextRequest;
import com.navneet.openai.mongo.model.ContextData;

public interface ContextService {
    /**
     * Saves a new context based on the provided request.
     *
     * @param request the context request containing the details to be saved
     * @return the saved ContextData
     */
    ContextData saveContext(ContextRequest request);

    /**
     * Retrieves the context data for the given name.
     *
     * @param name the name of the context to retrieve
     * @return the retrieved ContextData
     */
    ContextData getContext(String name);

    /**
     * Updates the context data for the given name based on the provided request.
     *
     * @param name the name of the context to update
     * @param request the context request containing the updated details
     * @return the updated ContextData
     */
    ContextData updateContext(String name, ContextRequest request);
}
