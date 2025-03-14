package com.navneet.openai.controller;

import com.navneet.openai.models.request.ChatRequest;
import com.navneet.openai.models.response.ChatTextCompletionResponse;
import com.navneet.openai.service.ChatCompletionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/chat")
public class ChatController {
    @Autowired
    private ChatCompletionService service;

    @PostMapping("complete")
    public ChatTextCompletionResponse generateTextResponse(@RequestBody ChatRequest request){
        return service.generateTextResponse(request);
    }
}
