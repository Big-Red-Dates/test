package com.msy.model;

import java.io.Serializable;

public class EntityUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//用户id
	private String entityUserId;
//实体id
    private String entityId;

    public EntityUser(){}
    
    public String getEntityUserId() {
        return entityUserId;
    }

    public void setEntityUserId(String entityUserId) {
        this.entityUserId = entityUserId;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}
