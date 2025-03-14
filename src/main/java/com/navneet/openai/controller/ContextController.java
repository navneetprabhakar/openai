package com.navneet.openai.controller;

import com.navneet.openai.mapper.ContextMapper;
import com.navneet.openai.models.request.ContextRequest;
import com.navneet.openai.mongo.model.ContextData;
import com.navneet.openai.service.ContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/context")
public class ContextController {

    @Autowired
    private ContextService contextService;

    /**
     * Saves a new context based on the provided request.
     *
     * @param request the context request containing the details to be saved
     * @return the saved ContextRequest
     */
    @PostMapping()
    public ContextRequest saveContext(@RequestBody ContextRequest request){
        return ContextMapper.INSTANCE.toContextRequest(contextService.saveContext(request));
    }

    @GetMapping()
    public ContextRequest getContext(@RequestParam("name") String name){
        return ContextMapper.INSTANCE.toContextRequest(contextService.getContext(name));
    }

    @PutMapping()
    public ContextRequest updateContext(@RequestParam("name") String name, @RequestBody ContextRequest request){
        return ContextMapper.INSTANCE.toContextRequest(contextService.updateContext(name, request));
    }

}
