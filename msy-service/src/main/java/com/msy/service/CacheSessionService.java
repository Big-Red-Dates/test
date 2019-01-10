package com.msy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.msy.model.Operator;


public interface CacheSessionService
{
	/**
	 * 用户登录
	 * <b>功能描述：</b><br/>
	 * <b>修改人：</b><br/>
	 * <b>修改时间：</b><br/>  
	 * <b>修改备注：</b><br/>
	 * @return
	 */
	public boolean txLogin(HttpServletRequest request, HttpServletResponse response,String sessionId, Operator user);

	/**
	 * 用户退出
	 * <b>功能描述：</b><br/>
	 * <b>修改人：</b><br/>
	 * <b>修改时间：</b><br/>  
	 * <b>修改备注：</b><br/>
	 * @return
	 */
	public boolean txLogout(HttpServletRequest request, HttpServletResponse response,String sessionId);

	/**
	 * 缓存获取用户信息
	 * <b>功能描述：</b><br/>
	 * <b>修改人：</b><br/>
	 * <b>修改时间：</b><br/>  
	 * <b>修改备注：</b><br/>
	 * @return
	 */
	public Operator getLoginUser(HttpServletRequest request, HttpServletResponse response,String sessionId);

	/**
	 * 用户是否登录
	 * <b>功能描述：</b><br/>
	 * <b>修改人：</b><br/>
	 * <b>修改时间：</b><br/>  
	 * <b>修改备注：</b><br/>
	 * @return
	 */
	public boolean isLogin(HttpServletRequest request, HttpServletResponse response,String sessionId);

	/**
	 * 用户是否强制退出
	 * <b>功能描述：</b><br/>
	 * <b>修改人：</b><br/>
	 * <b>修改时间：</b><br/>  
	 * <b>修改备注：</b><br/>
	 * @return
	 */
	public boolean quitLogin(HttpServletRequest request, HttpServletResponse response,String sessionId);

	/**
	 * 缓存头文件
	 * <b>功能描述：</b><br/>
	 * <b>修改人：</b><br/>
	 * <b>修改时间：</b><br/>  
	 * <b>修改备注：</b><br/>
	 * @return
	 */
	public boolean isExistsSessionHeader(String sessionId);

	public boolean deleteCacheSession(String sessionId);

	public boolean updateLoginUser(Operator loginUser);

	public boolean txLogin(String sessionId, Operator loginUser);

	public Operator getLoginUser(String sessionId);

	boolean isLogin(String sessionId);
}
