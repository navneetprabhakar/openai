package com.navneet.openai.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navneet.openai.constants.OpenApiConstants;
import com.navneet.openai.models.request.ChatRequest;
import com.navneet.openai.models.request.ChatTextCompletionRequest;
import com.navneet.openai.models.response.ChatTextCompletionResponse;
import com.navneet.openai.mongo.model.ContextData;
import com.navneet.openai.service.ChatCompletionService;
import com.navneet.openai.service.helper.ChatCompletionServiceHelper;
import com.navneet.openai.utils.ContextUtils;
import com.navneet.openai.utils.RestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ChatCompletionServiceImpl implements ChatCompletionService {

    @Autowired
    private RestUtils restUtils;
    @Autowired
    private OpenApiConstants constants;
    @Autowired
    private ChatCompletionServiceHelper helper;
    @Autowired
    private ContextUtils contextUtils;

    private static final ObjectMapper mapper=new ObjectMapper();

    @Override
    public ChatTextCompletionResponse generateTextResponse(ChatRequest request) {
        try {
            List<ChatRequest> context=new ArrayList<>();
            ContextData contextData=contextUtils.getContextData(request.getTheme());
            if(null!=contextData){
                contextData.getContext().forEach(c->context.add(ChatRequest.builder().message(c).build()));
            }
            ChatTextCompletionRequest payload=helper.prepareChatCompletionRequest(request, context);// Context messages to be added
            ResponseEntity<String> apiResponse=restUtils.restPostCall(constants.getBaseUrl()+constants.getVersion()+"chat/completions",
                    helper.authHeaders(constants.getApiKey()),null,payload);
            if(apiResponse.getStatusCode().is2xxSuccessful()){
                return mapper.readValue(apiResponse.getBody(), ChatTextCompletionResponse.class);
            }else{
                log.info("API request failed:{}",apiResponse.getBody());
            }
        } catch (Exception e) {
            log.error("An error occurred, please try again:",e);
            // TODO add error handling
        }
        return null;
    }
}
