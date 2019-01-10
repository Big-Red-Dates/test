package com.msy.mapper;

import java.util.List;

import com.msy.model.Entity;
import com.msy.model.EntityVo;
import com.msy.utils.PagerEntity;


public interface EntityMapper {
    
	/**
     *删除实体
     *@param entityId
	 *@return
     */
    int deleteByPrimaryKey(String entityId);

	/**
     *添加实体
     *@param entity
	 *@return
     */
    int insert(Entity entity);

    /**
     * 查询总理记录数
     * @param pagerEntity
     * @return
     */
	Integer countEntity(PagerEntity pagerEntity);

	/**
	 * 查询实体数量
	 * @param pagerEntity
	 * @return
	 */
	Entity searchEntityById(String entityId);
	
	/**
	 * 查询实体数量
	 * @param pagerEntity
	 * @return
	 */
	List<Entity> searchAllEntity(PagerEntity pagerEntity);

	/**
	 * 查询所有实体
	 * 
	 * @return
	 */
	List<Entity> selAllEntity();
	
	/**
     *修改实体
     *@param entityId
	 *@return
     */
    int updateByPrimaryKey(Entity entity);

    /**
     * 查询信息
     * @param entityId
     * @return
     */
    EntityVo selectEntityVo(String entityId);
}