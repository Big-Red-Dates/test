package com.msy.model;

import java.io.Serializable;

public class Movies implements Serializable{
	/**
	 * 用于获取分页电影信息
	 */
	private static final long serialVersionUID = -2959380887622247960L;
	
	/**
	 * 分页信息
	 */
	private Object pageInfo;
	
	/**
	 * 分页电影信息
	 */
	private Object datas;

	public Object getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(Object pageInfo) {
		this.pageInfo = pageInfo;
	}

	public Object getDatas() {
		return datas;
	}

	public void setDatas(Object datas) {
		this.datas = datas;
	}
	
	
}
