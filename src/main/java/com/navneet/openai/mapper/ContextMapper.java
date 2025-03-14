package com.navneet.openai.mapper;

import com.navneet.openai.models.request.ContextRequest;
import com.navneet.openai.mongo.model.ContextData;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ContextMapper {

    ContextMapper INSTANCE = Mappers.getMapper(ContextMapper.class);

    /**
     * Converts a ContextRequest object to a ContextData object.
     */
    ContextData toContextData(ContextRequest request);

    /**
     * Converts a ContextData object to a ContextRequest object.
     */
    ContextRequest toContextRequest(ContextData contextData);
}
