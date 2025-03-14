package com.navneet.openai.service;

import com.navneet.openai.models.request.ChatRequest;
import com.navneet.openai.models.response.ChatTextCompletionResponse;

public interface ChatCompletionService {

    /**
     * Generates a text response based on the provided chat request.
     *
     * @param request the chat request containing the details to generate the response
     * @return the generated ChatTextCompletionResponse
     */
    ChatTextCompletionResponse generateTextResponse(ChatRequest request);
}
