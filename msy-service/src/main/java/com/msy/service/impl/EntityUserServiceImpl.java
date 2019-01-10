package com.msy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.mapper.EntityUserMapper;
import com.msy.model.EntityUser;
import com.msy.service.EntityUserService;

@Service
@Transactional
public class EntityUserServiceImpl implements EntityUserService {

	@Autowired
	private EntityUserMapper entityUserMapper;
	
	@Override
	public int deleteByPrimaryKey(String entityUserId) {
		return this.entityUserMapper.deleteByPrimaryKey(entityUserId);
	}

	@Override
	public int insert(EntityUser record) {
		return this.entityUserMapper.insert(record);
	}

	@Override
	public int insertSelective(EntityUser record) {
		return entityUserMapper.insertSelective(record);
	}

	@Override
	public EntityUser selectByPrimaryKey(String entityUserId) {
		return entityUserMapper.selectByPrimaryKey(entityUserId);
	}

	@Override
	public int updateByPrimaryKeySelective(EntityUser record) {
		return entityUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EntityUser record) {
		return entityUserMapper.updateByPrimaryKey(record);
	}

}
