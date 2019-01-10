package com.msy.model;

import java.io.Serializable;
import java.util.Date;

import com.msy.constant.Constants;

public class Result implements Serializable{

	/**
	 * 统一返回对象
	 */
	private static final long serialVersionUID = 407711324256460645L;

	private String resultCode = Constants.RESULT_SUCCESS;//接口错误码
	private String resultMsg = "成功";//错误信息
	private Object resultData;//业务数据
	private long startTime=new Date().getTime();//时间戳

/**
	 * @return startTime
	 */
	public synchronized long getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime 要设置的 startTime
	 */
	public synchronized void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	//	long startTime=new Date().getTime();
	public Result(){}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Object getResultData() {
		return resultData;
	}

	public void setResultData(Object resultData) {
		this.resultData = resultData;
	}
	
	
}
