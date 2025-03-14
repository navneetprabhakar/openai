package com.navneet.openai.models.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContextRequest {
    private String name;
    private String description;
    private List<String> context;
}
