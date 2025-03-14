package com.navneet.openai.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.navneet.openai.models.MessageObject;
import com.navneet.openai.models.Usage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

/**
 * @author navneetprabhakar
 * document: https://platform.openai.com/docs/api-reference/chat/create
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatTextCompletionResponse {
    private String id;
    private String object;
    private Long created;
    private String model;
    private String system_fingerprint;
    private List<Choices> choices;
    private Usage usage;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Choices{
        private Integer index;
        private MessageObject message;
        private Map<String, Object> logprobs;
        private String finish_reason;
    }

}


