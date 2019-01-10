package com.msy.model;

import java.io.Serializable;
import java.util.Date;

public class PlayRecord implements Serializable{
    /**
	 * 开通记录表类
	 */
	private static final long serialVersionUID = 8671286324111216868L;

	/**
     *订单房间id
     */
    private String orderRoomId;

    /**
     *房间id
     */
    private String roomId;

    /**
     *订单id
     */
    private String orderId;

    /**
     *创建时间
     */
    private Date createdTime;

    /**
     *有效时间
     */
    private Date activeTime;
    
    /**
     *关联订单表
     */
    private Order order;

    public String getOrderRoomId() {
        return orderRoomId;
    }

    public void setOrderRoomId(String orderRoomId) {
        this.orderRoomId = orderRoomId == null ? null : orderRoomId.trim();
    }


    public String getRoomId() {
        return roomId;
    }


    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }


    public String getOrderId() {
        return orderId;
    }


    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }


    public Date getCreatedTime() {
        return createdTime;
    }


    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
    
}