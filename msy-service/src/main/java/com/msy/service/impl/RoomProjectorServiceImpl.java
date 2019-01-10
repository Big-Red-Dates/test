package com.msy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.mapper.RoomProjectorMapper;
import com.msy.model.Result;
import com.msy.model.RoomProjector;
import com.msy.service.RoomProjactorService;
import com.msy.utils.Pager;
import com.msy.utils.PagerEntity;
@Service
@Transactional
public class RoomProjectorServiceImpl implements RoomProjactorService {

	@Autowired
	private RoomProjectorMapper roomprojectorMapper;
	
	@Override
	public Result getAllRoomPro(Pager<RoomProjector> pager) throws Exception {
		// TODO Auto-generated method stub
		Result rb = new Result();
		RoomProjector rp = new RoomProjector();
		PagerEntity pagerEntity = new PagerEntity(rp,pager.getOffset(),pager.getPageSize(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null);
		if(pager.getDataSize() < pagerEntity.getOffset()){
			pagerEntity.setOffset(0);
		}
		
		pager.setBeanList(roomprojectorMapper.selAll(pagerEntity));
		rb.setResultData(pager);
		return rb;
	}

	@Override
	public int add(RoomProjector roomprojector) throws Exception {
		// TODO Auto-generated method stub
		return this.roomprojectorMapper.insert(roomprojector);
	}

	@Override
	public RoomProjector getRoomProjectorById(String projectorId) throws Exception {
		// TODO Auto-generated method stub
		return this.roomprojectorMapper.selectByProjectorId(projectorId);
	}

	@Override
	public RoomProjector getRoomProjectorByroomId(String roomId) throws Exception {
		// TODO Auto-generated method stub
		return this.roomprojectorMapper.selectByroomId(roomId);
	}

}
