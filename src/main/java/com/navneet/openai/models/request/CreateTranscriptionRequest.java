package com.navneet.openai.models.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author navneetprabhakar
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateTranscriptionRequest {
    // Multipart file to be uploaded as part of HTTP Request
    private String model;
    private String language;
    private String prompt;
    private String response_format;
    private Double temperature;
    private List<Object> timestamp_granularities;
}
