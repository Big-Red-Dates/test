package com.msy.model;

import java.io.Serializable;

/**
 * 
 * @author jung.chen add by 2018.04.01
 *
 */
public class RoomProjector implements Serializable{
    /**
	 * 房间投影仪实体类
	 */
	private static final long serialVersionUID = -5784300809668410839L;

	/**
     *房间ID
     */
	private String roomId;

	/**
     *投影仪ID
     */
    private String projectorId;
    
    /**
     *关联的开通记录实体
     */
    private PlayRecord playrecord;
    
    /**
     *关联的开通记录实体
     */
    private EntityRoom entityroom;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getProjectorId() {
        return projectorId;
    }

    public void setProjectorId(String projectorId) {
        this.projectorId = projectorId;
    }

	public PlayRecord getPlayrecord() {
		return playrecord;
	}

	public void setPlayrecord(PlayRecord playrecord) {
		this.playrecord = playrecord;
	}

	public EntityRoom getEntityroom() {
		return entityroom;
	}

	public void setEntityroom(EntityRoom entityroom) {
		this.entityroom = entityroom;
	}
    
}