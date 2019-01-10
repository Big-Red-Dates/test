package com.msy.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msy.model.SystemTime;
import com.msy.service.SystemTimeService;
@Service
@Transactional
public class SystemTimeServiceImpl implements SystemTimeService {
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	SystemTime systemTime = new SystemTime();
	
	@Override
	public SystemTime getTime() {
		// TODO Auto-generated method stub
			String datestr = dateFormat.format(new Date());
			systemTime.setDateStr(datestr);
		return systemTime;
	}

}
