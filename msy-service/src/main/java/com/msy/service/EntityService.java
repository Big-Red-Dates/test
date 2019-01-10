package com.msy.service;

import java.util.List;

import com.msy.model.Entity;
import com.msy.model.EntityVo;
import com.msy.model.Order;
import com.msy.model.Result;
import com.msy.utils.Pager;


/**
 * 实体（酒店或会所）服务
 * @author add by jung.chen
 *
 */
public interface EntityService {
	/**
	 * 添加实体信息接口
	 * @param entity
	 */
	int add(Entity entity) throws Exception ;
		

	/**
     * 分页查询实体列表
	 * @param pager 
     * @return
     */
	Result getAllEntity(Pager<Entity> pager)  throws Exception;
	
	 /**
     * 查询信息
     * @param entityId
     * @return
     */
    EntityVo selectEntityVo(String entityId);
    
    /**
     * 根据id查询实体信息
     * @param entityId
     * @return
     */
    Entity selectEntityByid(String entityId);
    
    /**
     * 查询所有实体信息（不传参数）
     * 
     * @return
     */
    List<Entity> getall();
	
	/**
	 * 根据id删除房间信息
	 * @param id
	 * @throws Exception
	 */
//	void delEntityById(String id)throws Exception;
	
	/**
	 * 根据id修改房间信息
	 * @param id
	 * @throws Exception
	 */
	int updateEntityById(Entity entity)throws Exception;
}
