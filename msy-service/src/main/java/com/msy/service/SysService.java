package com.msy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msy.model.Result;

public interface SysService {

	
	/**
	 * 登录接口
	 * 
	 * @param request
	 *            请求
	 * @param response
	 *            响应
	 * @param code
	 *            用户登录名
	 * @param password
	 *            密码
	 * @param client
	 *            使用客户端
	 * @return
	 */
	public Result txLogin(HttpServletRequest request, HttpServletResponse response, String code, String password,String userType);

	/**
	 * 登出接口
	 * 
	 * @param request
	 *            请求
	 * @return
	 */
	public Result txLogout(HttpServletRequest request);


}
