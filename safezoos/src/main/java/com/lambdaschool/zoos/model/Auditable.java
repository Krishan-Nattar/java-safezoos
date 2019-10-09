package com.lambdaschool.zoos.model;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import java.util.Date;

import static javax.persistence.TemporalType.TIMESTAMP;


@MappedSuperclass //Super class means we're never going to instantiate it, or make a direct object of it
@EntityListeners(AuditingEntityListener.class) //Listeners are servivces that run in the background that spring manages for us
// Every time something happens to an @Entity, spring will check this class to see if it needs to update/do something.
//We are specifically working with the AuditingEntityListener
abstract class Auditable //For any field we want all these fields, we just need to extend auditable
{
    @CreatedBy //The field is named based on these annotations
    protected String createdBy; //These can be technically named anything as the actual names are created by the annotations that come before.
    //It is essentially a placeholder

    @CreatedDate //Spring adds underscores.
    @Temporal(TIMESTAMP) //This tells Spring how we want the date to be formatted.
    protected Date createdDate;

    @LastModifiedBy //These annotations are essentially "Setters"
    protected String lastModifiedBy;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;


}