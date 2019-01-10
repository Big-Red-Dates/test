package com.msy.service;

import java.util.Date;

import com.msy.model.PlayRecord;

/**
 * 开通记录表服务
 * @author add by jung.chen
 *
 */
public interface PlayRecordService {

	/**
	 * 添加开通记录表信息接口
	 * @param entityroom
	 */
	void add(PlayRecord playRecord) throws Exception ;
	
	/**
	 * 获取开通记录表信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PlayRecord getPlayRecordById(String id) throws Exception ;
	
	/**
	 * 根据roomid获取开通记录表信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	PlayRecord getPlayRecordByRoomid(String roomid) throws Exception ;
	
	
	/**
	 * 根据id删除开通记录表信息
	 * @param id
	 * @throws Exception
	 */
	void delPlayRecordById(String id)throws Exception;
	
	/**
	 * 根据id修改开通记录表信息
	 * @param id
	 * @throws Exception
	 */
	void updatePlayRecordById(PlayRecord playRecord)throws Exception;
	
	/**
	 * 根据id和当前时间查询开通记录表信息
	 * @param id
	 * @throws Exception
	 */
	PlayRecord getPlayRecordByIdAndcurrTime(String roomId,Date currtime)throws Exception;
	
	
}
