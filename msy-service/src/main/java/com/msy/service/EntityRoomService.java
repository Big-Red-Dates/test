package com.msy.service;

import java.util.List;


import com.msy.model.EntityRoom;
import com.msy.model.Result;
import com.msy.utils.Pager;

/**
 * 实体房间服务
 * @author add by jung.chen
 *
 */
public interface EntityRoomService {

	/**
	 * 添加实体房间信息接口
	 * @param entityroom
	 */
	int add(EntityRoom entityroom) throws Exception ;
	
	/**
	 * 获取实体房间信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	EntityRoom getEntityRoomById(String id) throws Exception ;
	
	/**
	 * 获取实体房间列表信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	List<EntityRoom> getEntityRoomListById(String id) throws Exception ;
	
	/**
	 * 获取所有实体房间列表信息
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	List<EntityRoom> getEntityRoomAll() throws Exception ;
	
	/**
	 * 根据id删除房间信息
	 * @param id
	 * @throws Exception
	 */
	void delEntityRoomById(String id)throws Exception;
	
	/**
	 * 根据id修改房间信息
	 * @param id
	 * @throws Exception
	 */
	void updateEntityRoomById(EntityRoom entityroom)throws Exception;

}
