package com.navneet.openai.mongo.model;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * @author navneetprabhakar
 * This class sets the system generated default values for mongo document
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AuditEntity {

    @Id
    private String id;

    @CreatedBy
    private String createdByUser;

    @CreatedDate
    private Date creationDate;

    @LastModifiedBy
    private String lastModifiedByUser;

    @LastModifiedDate
    private Date lastModifiedDate;
}
