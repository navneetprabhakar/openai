package com.navneet.openai.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.navneet.openai.models.MessageObject;
import lombok.*;

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
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatTextCompletionRequest {
    private List<MessageObject> messages;
    private String model;
    // Optional fields
    private Boolean store;
    private Map<String, Object> metadata; // to be updated upon testing
    private Double frequency_penalty;
    private Map<String,Object> logit_bias; // Represents JSON
    private Boolean logprobs;
    private Integer top_logprobs;
    @Deprecated
    private Integer max_tokens;
    private Integer max_completion_tokens; // Replacing max_tokens
    private Integer n;
    private Double presence_penalty;
    private Map<String, Object> response_format; // JSON Object
    private Integer seed;
    private String service_tier;
    private String stop; // can also be an array, to be changed as per use case;
    private Boolean stream;
    private Map<String, Object> stream_options;
    private Double temperature;
    private Double top_p;
    private List<Tools> tools;
    private String tool_choice;
    private Boolean parallel_tool_calls;
    private String user;



    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Tools{
        private String type;
        private Function function;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Function{
        private String name;
        private String description;
        private Map<String, Object> parameters;
        private Boolean strict;
    }
}
