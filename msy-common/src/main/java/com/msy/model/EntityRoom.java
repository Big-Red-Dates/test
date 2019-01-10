package com.msy.model;

import java.io.Serializable;

public class EntityRoom  implements Serializable{
    /**
	 *实体(酒店或会所)房间类
	 */
	private static final long serialVersionUID = 6920869040124617118L;

	/**
     *房间ID
     */
    private String roomId;

    /**
     * 房间号码
     */
    private String roomNo;

    /**
     *房间名称
     */
    private String roomName;

    /**
     *实体ID
     */
    private String entityId;
    
    /**
     *关联实体
     */
    private Entity entity;
    
    public String getRoomId() {
        return roomId;
    }

   
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomNo() {
        return roomNo;
    }

 
    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }


    public String getRoomName() {
        return roomName;
    }


    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }


    public String getEntityId() {
        return entityId;
    }


    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }


	public Entity getEntity() {
		return entity;
	}


	public void setEntity(Entity entity) {
		this.entity = entity;
	}
    
}