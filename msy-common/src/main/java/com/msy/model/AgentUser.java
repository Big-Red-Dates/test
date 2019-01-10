package com.msy.model;

import java.io.Serializable;

public class AgentUser implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5178456626457079991L;

	private String agentUserId;

    private String agentUserName;
//代理商身份（0.主账号 报备账号记录t_operator的operator_id字段）
    private String agentType;

    private String agentId;

    public AgentUser (){}

	public String getAgentUserId() {
		return agentUserId;
	}

	public void setAgentUserId(String agentUserId) {
		this.agentUserId = agentUserId;
	}

	public String getAgentUserName() {
		return agentUserName;
	}

	public void setAgentUserName(String agentUserName) {
		this.agentUserName = agentUserName;
	}

	public String getAgentType() {
		return agentType;
	}

	public void setAgentType(String agentType) {
		this.agentType = agentType;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}
    
    
}