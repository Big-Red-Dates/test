package com.msy.mapper;

import java.util.List;

import com.msy.model.Entity;
import com.msy.model.EntityRoom;
import com.msy.utils.PagerEntity;



public interface EntityRoomMapper {

  
	/**
     * 通过房间Id删除记录
     * @param roomId
     * @return
     */
    int deleteByPrimaryKey(String roomId);

    /**
     * 添加房间信息
     * @param entityroom
     * @return
     */
    int insert(EntityRoom entityroom);

    /**
     * 通过房间Id查询房间信息
     * @param roomId
     * @return
     */
    EntityRoom selectByPrimaryKey(String roomId);
    
    /**
     * 通过房间Id查询房间信息并关联实体表
     * @param roomId
     * @return
     */
    EntityRoom selectByroomid(String roomId);
    
    /**
     * 通过实体Id查询一些房间信息
     * @param entityId
     * @return
     */
    List<EntityRoom> selectListByPrimaryKey(String entityId);

    /**
     * 查询总理记录数
     * @param pagerEntity
     * @return
     */
	Integer countEntityroom(PagerEntity pagerEntity);
	
    /**
     * 查询所有房间信息
     * @param entityId
     * @return
     */
    List<EntityRoom> selectall();
    /**
     * 更新房间信息
     * @param entityroom
     * @return
     */
    int updateByPrimaryKey(EntityRoom entityroom);
}