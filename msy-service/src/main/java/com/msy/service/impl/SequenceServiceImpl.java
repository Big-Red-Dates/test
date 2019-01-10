package com.msy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.annotation.DataSource;
import com.msy.mapper.SequenceMapper;
import com.msy.service.SequenceService;

@Service
@Transactional
@DataSource("ds_master")
public class SequenceServiceImpl implements SequenceService {

	@Autowired
	private SequenceMapper sequenceMapper;
	
	@Override
	public Long getOrderNoSeqVal() {
		return this.sequenceMapper.getOrderNoSeqVal();
	}

}
