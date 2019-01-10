package com.msy.model;

import java.io.Serializable;

/**
 * 操作员用户
 * @author yanz.wu by 20180328
 *
 */
public class Operator implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1491951209613312107L;

	/**
	 * 操作员ID
	 */
	private String operatorId;
	/**
	 * 操作员名称
	 */
    private String operatorLoginName;
    /**
	 * 操作员密码
	 */
    private String operatorLoginPassword;
    /**
	 * 操作员昵称
	 */
    private String operatorLoginNick;
    /**
	 * 实体ID
	 */
    private String userType;

    public Operator(){}
    
    public String getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorLoginName() {
        return operatorLoginName;
    }

    public void setOperatorLoginName(String operatorLoginName) {
        this.operatorLoginName = operatorLoginName;
    }

    public String getOperatorLoginPassword() {
        return operatorLoginPassword;
    }

    public void setOperatorLoginPassword(String operatorLoginPassword) {
        this.operatorLoginPassword = operatorLoginPassword;
    }

    public String getOperatorLoginNick() {
        return operatorLoginNick;
    }

    public void setOperatorLoginNick(String operatorLoginNick) {
        this.operatorLoginNick = operatorLoginNick;
    }

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}