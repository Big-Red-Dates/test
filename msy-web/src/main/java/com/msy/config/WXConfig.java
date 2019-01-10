package com.msy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "weixin")
public class WXConfig {

	/**
	 * 微信商户ID
	 */
	private String merchatId;
	/**
	 * 微信商户密码
	 */
	private String merchatIdKey;
	/**
	 * 公众号ID
	 */
	private String appId;
	/**
	 * 公众号密码
	 */
	private String appSecret;
	/**
	 * 请求获取code权限地址
	 */
	private String authUrl;
	/**
	 * 获取openid权限地址
	 */
	private String openIdUrl;
	/**
	 * 重定向地址
	 */
	private String rediretUri;
	/**
	 * 回掉地址
	 */
	private String notifyUrl;
	/**
	 * 微信预支付地址
	 */
	private String payUrl;
	/**
	 * ip地址
	 */
	private String ip;
	/**
	 * 域名
	 */
	private String domain;
	/**
	 * 本地获取code路径
	 */
	private String codeUrl;
	/**
	 * 关注用的appId
	 */
	private String followappId;
	/**
	 * 关注用的appSecret
	 */
	private String followappSecret;
	
	public WXConfig(){}

	
	public String getPayUrl() {
		return payUrl;
	}


	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}


	public String getCodeUrl() {
		return codeUrl;
	}


	public void setCodeUrl(String codeUrl) {
		this.codeUrl = codeUrl;
	}


	public String getDomain() {
		return domain;
	}


	public void setDomain(String domain) {
		this.domain = domain;
	}


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMerchatId() {
		return merchatId;
	}

	public void setMerchatId(String merchatId) {
		this.merchatId = merchatId;
	}

	public String getMerchatIdKey() {
		return merchatIdKey;
	}

	public void setMerchatIdKey(String merchatIdKey) {
		this.merchatIdKey = merchatIdKey;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getRediretUri() {
		return rediretUri;
	}

	public void setRediretUri(String rediretUri) {
		this.rediretUri = rediretUri;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getOpenIdUrl() {
		return openIdUrl;
	}

	public void setOpenIdUrl(String openIdUrl) {
		this.openIdUrl = openIdUrl;
	}


	public String getFollowappId() {
		return followappId;
	}


	public void setFollowappId(String followappId) {
		this.followappId = followappId;
	}


	public String getFollowappSecret() {
		return followappSecret;
	}


	public void setFollowappSecret(String followappSecret) {
		this.followappSecret = followappSecret;
	}
	
	
}
