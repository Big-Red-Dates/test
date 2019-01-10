package com.msy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.mapper.EntityRoomMapper;
import com.msy.model.EntityRoom;
import com.msy.model.Result;
import com.msy.service.EntityRoomService;
import com.msy.utils.Pager;
import com.msy.utils.PagerEntity;
@Service
@Transactional
public class EntityRoomServiceImpl implements EntityRoomService {

	@Autowired
	private EntityRoomMapper entityRoomMapper;
	
	@Override
	public int add(EntityRoom entityroom) throws Exception {
		return this.entityRoomMapper.insert(entityroom);
	}

	@Override
	public EntityRoom getEntityRoomById(String id) throws Exception {
		return this.entityRoomMapper.selectByPrimaryKey(id);
	}

	@Override
	public void delEntityRoomById(String id) throws Exception {
		this.entityRoomMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateEntityRoomById(EntityRoom entityroom) throws Exception {
		this.entityRoomMapper.updateByPrimaryKey(entityroom);
	}

	@Override
	public List<EntityRoom> getEntityRoomListById(String id) throws Exception {
		return this.entityRoomMapper.selectListByPrimaryKey(id);
	}

	@Override
	public List<EntityRoom> getEntityRoomAll() throws Exception {
		return this.entityRoomMapper.selectall();
	}

}
