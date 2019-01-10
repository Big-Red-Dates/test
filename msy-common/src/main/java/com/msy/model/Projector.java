package com.msy.model;

import java.io.Serializable;

public class Projector implements Serializable {
	/**
	 * 投影仪实体类
	 */
	private static final long serialVersionUID = -7338160227519064859L;
	
	/**
	 * 投影仪ID
	 */
	private String projectorId;
	
	/**
	 * 投影仪设备ID
	 */
	private String projectorDeviceId;
	
	/**
	 * 投影仪设备名
	 */
	private String projectorName;
	
	/**
	 * 投影仪安卓版本
	 */
	private String projectorAndroidVer;
	
	/**
	 * 投影仪所在房间号
	 */
	private String roomId;

	public String getProjectorId() {
		return projectorId;
	}

	public void setProjectorId(String projectorId) {
		this.projectorId = projectorId;
	}

	public String getProjectorDeviceId() {
		return projectorDeviceId;
	}

	public void setProjectorDeviceId(String projectorDeviceId) {
		this.projectorDeviceId = projectorDeviceId;
	}

	public String getProjectorName() {
		return projectorName;
	}

	public void setProjectorName(String projectorName) {
		this.projectorName = projectorName;
	}

	public String getProjectorAndroidVer() {
		return projectorAndroidVer;
	}

	public void setProjectorAndroidVer(String projectorAndroidVer) {
		this.projectorAndroidVer = projectorAndroidVer;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	
}
