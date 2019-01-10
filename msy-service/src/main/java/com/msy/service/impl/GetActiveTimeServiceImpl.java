package com.msy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.mapper.RoomProjectorMapper;
import com.msy.model.RoomProjector;
import com.msy.service.GetActiveTimeService;
@Service
@Transactional
public class GetActiveTimeServiceImpl implements GetActiveTimeService {

	@Autowired
	private RoomProjectorMapper roomprojectormapper;
	
	@Override
	public RoomProjector getroomIdByProjectorId(String projectorId) throws Exception {
		// TODO Auto-generated method stub
		return this.roomprojectormapper.selectByProjectorId(projectorId);
	}

}
