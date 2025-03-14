package com.navneet.openai.constants;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class OpenApiConstants {

    @Value("${open.ai.base.url}")
    private String baseUrl;
    @Value("${open.ai.version}")
    private String version;
    @Value("${open.ai.api.key}")
    private String apiKey;
}
