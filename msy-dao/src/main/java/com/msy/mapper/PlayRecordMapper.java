package com.msy.mapper;

import java.util.Date;

import com.msy.model.PlayRecord;



public interface PlayRecordMapper {
  
	/**
     *删除开通记录表
     *@param orderRoomId
	 *@return
     */
    int deleteByPrimaryKey(String orderRoomId);

    /**
     *添加开通记录表
     *@param playRecord
	 *@return
     */
    int insert(PlayRecord playRecord);

    /**
     *查询开通记录表
     *@param orderRoomId
	 *@return
     */
    PlayRecord selectByPrimaryKey(String orderRoomId);
    
    /**
     *根据roomid查询开通记录表
     *@param RoomId
	 *@return
     */
    PlayRecord selectByRoomId(String RoomId);
    
    /**
     *根据roomid和比较有效时间查询开通记录表
     *@param RoomId
	 *@return
     */
    PlayRecord selectByRoomIdAndActiveTime(String roomId,Date currtime);


    /**
     *更新开通记录表
     *@param orderRoomId
	 *@return
     */
    int updateByPrimaryKey(PlayRecord playRecord);
}