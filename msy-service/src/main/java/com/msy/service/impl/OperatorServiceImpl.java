package com.msy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.annotation.DataSource;
import com.msy.mapper.OperatorMapper;
import com.msy.model.Operator;
import com.msy.service.OperatorService;
@Service
@Transactional
@DataSource("ds_master")
public class OperatorServiceImpl implements OperatorService {

	@Autowired
	private OperatorMapper operatorMapper;
	
	@Override
	public int deleteByPrimaryKey(String operatorId) {
		return this.operatorMapper.deleteByPrimaryKey(operatorId);
	}

	@Override
	public int insert(Operator record) {
		return this.operatorMapper.insert(record);
	}

	@Override
	public int insertSelective(Operator record) {
		return this.operatorMapper.insertSelective(record);
	}

	@Override
	public Operator selectByPrimaryKey(String operatorId) {
		return this.operatorMapper.selectByPrimaryKey(operatorId);
	}

	@Override
	public Operator selectByName(String operatorName) {
		return this.operatorMapper.selectByName(operatorName);
	}

	@Override
	public int updateByPrimaryKeySelective(Operator record) {
		return this.operatorMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Operator record) {
		return this.operatorMapper.updateByPrimaryKey(record);
	}

}
