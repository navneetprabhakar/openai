package com.navneet.openai.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(value = "contextData")
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContextData extends AuditEntity {

    private String name;
    private String description;
    private List<String> context;
}
