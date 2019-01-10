package com.msy.service;

import com.msy.model.SystemTime;

/**
 * 系统时间服务服务
 *
 */
public interface SystemTimeService {
	
	/**
	 * 获取系统时间接口
	 * @param dateType
	 */
	SystemTime getTime();
}
