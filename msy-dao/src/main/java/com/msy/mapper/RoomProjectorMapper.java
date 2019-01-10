package com.msy.mapper;



import java.util.List;

import com.msy.model.RoomProjector;
import com.msy.utils.PagerEntity;


public interface RoomProjectorMapper {


	/**
     *添加房间投影仪记录
     *@param roomProjector
	 *@return
     */
    int insert(RoomProjector roomProjector);



     /**
     *查询房间投影仪记录
     *@param roomProjector
	 *@return
     */
     RoomProjector selectByProjectorId(String projectorId);
     
     /**
      *根据房间id查询房间投影仪记录
      *@param roomProjector
 	  *@return
      */
      RoomProjector selectByroomId(String roomId);
     
     /**
      * 查询总理记录数
      * @param pagerEntity
      * @return
      */
 	Integer countRoomProjector(PagerEntity pagerEntity);
     
     /**
      *分页查询房间投影仪记录
      *@param roomProjector
 	  *@return
      */
      List<RoomProjector> selAll(PagerEntity pagerentity);

    
}