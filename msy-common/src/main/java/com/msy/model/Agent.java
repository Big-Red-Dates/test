package com.msy.model;

import java.io.Serializable;

public class Agent implements Serializable {
    /**
	 * 代理商主账户
	 */
	private static final long serialVersionUID = -8025783716344158298L;
/**
 * 代理商id
 */
	private String agentId;

    private Integer agentBinsId;

    private String agentName;

    private String agentAreaProv;

    private String agentAreaCity;

    private String managerName;

    private String managerPhone;
 
    private Double agentRate;

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
    }

    public Integer getAgentBinsId() {
        return agentBinsId;
    }

    public void setAgentBinsId(Integer agentBinsId) {
        this.agentBinsId = agentBinsId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName == null ? null : agentName.trim();
    }

    public String getAgentAreaProv() {
        return agentAreaProv;
    }

    public void setAgentAreaProv(String agentAreaProv) {
        this.agentAreaProv = agentAreaProv == null ? null : agentAreaProv.trim();
    }

    public String getAgentAreaCity() {
        return agentAreaCity;
    }

    public void setAgentAreaCity(String agentAreaCity) {
        this.agentAreaCity = agentAreaCity == null ? null : agentAreaCity.trim();
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName == null ? null : managerName.trim();
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone == null ? null : managerPhone.trim();
    }

    public Double getAgentRate() {
        return agentRate;
    }

    public void setAgentRate(Double agentRate) {
        this.agentRate = agentRate;
    }
}