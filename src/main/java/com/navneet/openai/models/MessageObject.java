package com.navneet.openai.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

/**
 * @author navneetprabhakar
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageObject {

    private String role;
    private String content;
    private String name;
    // Optional
    private String refusal;// Assistant Message payload
    private ToolCalls tool_calls;
    @Deprecated
    private Function function_call;

    // Mandatory for tool message;
    private String tool_call_id;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class ToolCalls{
        private String id;
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
        private String arguments;
    }
}
