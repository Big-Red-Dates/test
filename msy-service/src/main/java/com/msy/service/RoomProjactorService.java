package com.msy.service;


import com.msy.model.Result;
import com.msy.model.RoomProjector;
import com.msy.utils.Pager;

public interface RoomProjactorService {
	/**
     * 分页查询实体设备列表
	 * @param pager 
     * @return
     */
	Result getAllRoomPro(Pager<RoomProjector> pager)  throws Exception;
	
	/**
     * 添加实体设备列表
	 * @param pager 
     * @return
     */
	int add(RoomProjector roomprojector)  throws Exception;
	
	/**
     * 获取房间设备信息
	 * @param pager 
     * @return
     */
	RoomProjector getRoomProjectorById(String projectorId)  throws Exception;
	
	/**
     * 获取房间设备信息
	 * @param pager 
     * @return
     */
	RoomProjector getRoomProjectorByroomId(String roomId)  throws Exception;
	
	
}
