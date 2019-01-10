package com.msy.model;

import java.io.Serializable;

import com.msy.constant.Constants;

public class ResultForMovie implements Serializable{

	/**
	 * 用于返回电影信息给投影端
	 */
	private static final long serialVersionUID = 7964101236231927362L;
	
	private String status = Constants.RESULTMO_SUCCESS;//接口错误码
	private String apiDesp;
	private String resultMsg = "成功";
	private Object datas;//业务数据
	
	public ResultForMovie(){}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApiDesp() {
		return apiDesp;
	}

	public void setApiDesp(String apiDesp) {
		this.apiDesp = apiDesp;
	}

	
	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	};

}
