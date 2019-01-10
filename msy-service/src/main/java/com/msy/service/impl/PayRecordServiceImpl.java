package com.msy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.annotation.DataSource;
import com.msy.mapper.PayRecordMapper;
import com.msy.model.PayRecord;
import com.msy.service.PayRecordService;

@Service
@Transactional
@DataSource("ds_master")
public class PayRecordServiceImpl implements PayRecordService {

	@Autowired
	private PayRecordMapper payRecordMapper;
	
	@Override
	public int insert(PayRecord record) {
		return this.payRecordMapper.insert(record);
	}

	@Override
	public PayRecord selectByPrimaryKey(String orderRoomId) {
		return this.payRecordMapper.selectByPrimaryKey(orderRoomId);
	}

	@Override
	public List<PayRecord> selectAll() {
		return this.payRecordMapper.selectAll();
	}

}
