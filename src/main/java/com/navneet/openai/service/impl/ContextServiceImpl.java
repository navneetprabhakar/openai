package com.navneet.openai.service.impl;

import com.navneet.openai.mapper.ContextMapper;
import com.navneet.openai.models.request.ContextRequest;
import com.navneet.openai.mongo.model.ContextData;
import com.navneet.openai.service.ContextService;
import com.navneet.openai.utils.ContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContextServiceImpl implements ContextService {
    @Autowired
    private ContextUtils contextUtils;

    @Override
    public ContextData saveContext(ContextRequest request) {
        return contextUtils.saveContextData(ContextMapper.INSTANCE.toContextData(request));
    }

    @Override
    public ContextData getContext(String name) {
        return contextUtils.getContextData(name);
    }

    @Override
    public ContextData updateContext(String name, ContextRequest request) {
        ContextData contextData = contextUtils.getContextData(name);
        if(null != contextData) {
            contextData.setDescription(request.getDescription());
            contextData.setContext(request.getContext());
            return contextUtils.saveContextData(contextData);
        }
        log.info("Context with name {} not found", name);
        throw new RuntimeException("Context not found");
    }
}
