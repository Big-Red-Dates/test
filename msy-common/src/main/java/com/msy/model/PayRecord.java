package com.msy.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author yanz.wu add by 2018.04.07
 *
 */

public class PayRecord implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6095249160928506378L;

	/**
	 * 主键ID
	 */
	private String orderRoomId;

	/**
	 * 房间ID
	 */
    private String roomId;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 有效时间
     */
    private Date activeTime;

    public String getOrderRoomId() {
        return orderRoomId;
    }

    public void setOrderRoomId(String orderRoomId) {
        this.orderRoomId = orderRoomId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
}