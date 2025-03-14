package com.navneet.openai.service.helper;

import com.navneet.openai.constants.OpenApiConstants;
import com.navneet.openai.models.MessageObject;
import com.navneet.openai.models.request.ChatRequest;
import com.navneet.openai.models.request.ChatTextCompletionRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class ChatCompletionServiceHelper {

    private static final String SYSTEM="system";
    private static final String USER="user";

    public ChatTextCompletionRequest prepareChatCompletionRequest(ChatRequest request, List<ChatRequest> context){
        List<MessageObject> messages = new ArrayList<>();
        if(!CollectionUtils.isEmpty(context)){
            // Adding historic context messages if any
            context.forEach(chat-> messages.add(MessageObject.builder().role(SYSTEM).content(chat.getMessage()).build()));
        }
        messages.add(MessageObject.builder().role(SYSTEM).content("You are a helpful assistant.").build());
        // Adding current message to the payload
        messages.add(MessageObject.builder().role(USER).content(request.getMessage()).build());
        return ChatTextCompletionRequest.builder()
                .messages(messages)
                .model(request.getModel())
                .build();
    }

    public Map<String, String> authHeaders(String apiKey){
        Map<String, String> headers=new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Authorization","Bearer "+apiKey);
        return headers;
    }
}
