package com.msy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.mapper.EntityMapper;
import com.msy.model.Entity;
import com.msy.model.EntityVo;
import com.msy.model.Result;
import com.msy.service.EntityService;
import com.msy.utils.Pager;
import com.msy.utils.PagerEntity;

@Service
@Transactional
public class EntityServiceImpl implements EntityService {

	@Autowired
	private EntityMapper entityMapper;
	
	@Override
	public Result getAllEntity(Pager<Entity> pager) throws Exception {
		// TODO Auto-generated method stub
		Result rb = new Result();
		Entity entity = new Entity();
		PagerEntity pagerEntity = new PagerEntity(entity,pager.getOffset(),pager.getPageSize(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, 0, null, null);
		if(pager.getDataSize() < pagerEntity.getOffset()){
			pagerEntity.setOffset(0);
		}
		
		pager.setBeanList(entityMapper.searchAllEntity(pagerEntity));
		rb.setResultData(pager);
		return rb;
	}
	
	@Override
	public EntityVo selectEntityVo(String entityId) {
		return this.entityMapper.selectEntityVo(entityId);
	}

	@Override
	public int add(Entity entity) throws Exception {
		// TODO Auto-generated method stub
		return this.entityMapper.insert(entity);
	}

	@Override
	public Entity selectEntityByid(String entityId) {
		// TODO Auto-generated method stub
		return this.entityMapper.searchEntityById(entityId);
	}

	@Override
	public int updateEntityById(Entity entity) throws Exception {
		// TODO Auto-generated method stub
		return this.entityMapper.updateByPrimaryKey(entity);
	}

	@Override
	public List<Entity> getall() {
		// TODO Auto-generated method stub
		return this.entityMapper.selAllEntity();
	}

}
