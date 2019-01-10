package com.msy.model;

import java.util.Date;


public class AgentUserVo extends AgentUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7885497283193351774L;

	//实体名称
	private String entityName;
	//代理商id
	private String agentId;
	//开始时间
	private Date startDt;
	//结束时间
	private Date endDt;
	private String startDtStr;
	private String endDtStr;
	
	public AgentUserVo(){}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}

	public String getStartDtStr() {
		return startDtStr;
	}

	public void setStartDtStr(String startDtStr) {
		this.startDtStr = startDtStr;
	}

	public String getEndDtStr() {
		return endDtStr;
	}

	public void setEndDtStr(String endDtStr) {
		this.endDtStr = endDtStr;
	}

	public Date getStartDt() {
		return startDt;
	}
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
	public Date getEndDt() {
		return endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
	
	
}
