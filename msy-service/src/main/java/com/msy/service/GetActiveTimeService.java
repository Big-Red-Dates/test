package com.msy.service;

import com.msy.model.RoomProjector;

/**
 * 获取有效时间服务
 * @author add by jung.chen
 *
 */
public interface GetActiveTimeService {

	/**
	 * 获取有效时间信息
	 * @param roomid
	 * @return
	 * @throws Exception
	 */
	RoomProjector getroomIdByProjectorId(String projectorId) throws Exception ;

}
