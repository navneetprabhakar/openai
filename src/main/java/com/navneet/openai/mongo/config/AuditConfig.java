package com.navneet.openai.mongo.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author navneetprabhakar
 * This class sets default auditor name for mongo document
 */
@Component
public class AuditConfig implements AuditorAware<String> {
    private static String AUDITOR="action-tracker";
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(AUDITOR);
    }
}
