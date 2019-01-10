package com.msy.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.mapper.PlayRecordMapper;
import com.msy.model.PlayRecord;
import com.msy.service.PlayRecordService;

@Service
@Transactional
public class PlayRecordServiceImpl implements PlayRecordService {
	
	@Autowired
	private PlayRecordMapper playRecordMapper;
	
	@Override
	public void add(PlayRecord playRecord) throws Exception {
		// TODO Auto-generated method stub
		this.playRecordMapper.insert(playRecord);
	}

	@Override
	public PlayRecord getPlayRecordById(String id) throws Exception {
		// TODO Auto-generated method stub
		return this.playRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delPlayRecordById(String id) throws Exception {
		// TODO Auto-generated method stub
		this.playRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updatePlayRecordById(PlayRecord playRecord) throws Exception {
		// TODO Auto-generated method stub
		this.playRecordMapper.updateByPrimaryKey(playRecord);
	}

	@Override
	public PlayRecord getPlayRecordByRoomid(String roomid) throws Exception {
		// TODO Auto-generated method stub
		return this.playRecordMapper.selectByRoomId(roomid);
	}

	@Override
	public PlayRecord getPlayRecordByIdAndcurrTime(String roomId, Date currtime) throws Exception {
		// TODO Auto-generated method stub
		return this.playRecordMapper.selectByRoomIdAndActiveTime(roomId, currtime);
	}



}
