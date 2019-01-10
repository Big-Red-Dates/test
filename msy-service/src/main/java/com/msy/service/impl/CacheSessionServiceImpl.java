package com.msy.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msy.constant.Constants;
import com.msy.model.Operator;
import com.msy.service.CacheSessionService;
import com.msy.service.ICache;
import com.msy.service.SessionService;

import net.sf.ehcache.Cache;



@Service("cacheSessionService")
public class CacheSessionServiceImpl  implements CacheSessionService
{
	protected Log log = LogFactory.getLog(this.getClass().getName());
	
	@Autowired
    private SessionService sessionService;
	@Autowired
	protected  ICache cache;
	@Override
	public boolean txLogin(HttpServletRequest request, HttpServletResponse response, String sessionId, Operator user) {
		HttpSession session = request.getSession(false);

		if (null == session)
			return false;

		session.setAttribute(sessionId, user);
		session.setAttribute(Constants.GSM_SESSION, sessionId);

		cache.put(Constants.LoginUser + sessionId, user);
		
		log.debug("cache:"+cache + "login by sessionid:"+Constants.LoginUser + sessionId);
		
		return true;
	}
	
	@Override
	public boolean txLogout(HttpServletRequest request, HttpServletResponse response, String sessionId) {
		
		cache.put(Constants.LoginUser + sessionId, null);
		
		log.debug("login out sessionid : " + Constants.LoginUser + sessionId);
		return true;
		
	}
	
	@Override
	public boolean quitLogin(HttpServletRequest request, HttpServletResponse response, String sessionId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isExistsSessionHeader(String sessionId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteCacheSession(String sessionId) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean updateLoginUser(Operator loginUser) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean txLogin(String sessionId, Operator loginUser) {

		return false;
	}
    
	@Override
	public Operator getLoginUser(HttpServletRequest request, HttpServletResponse response,String sessionId)
	{
		if (isLogin(request, response,sessionId))
		{
			return (Operator) request.getSession().getAttribute(sessionId);
		}
		return null;
	}

	@Override
	public boolean isLogin(HttpServletRequest request, HttpServletResponse response,String sessionId)
	{
		boolean result = false;
		
		Operator user = (Operator) request.getSession().getAttribute(sessionId);
		
		if(null != user)
			result = true;
		
		return result;
	}

	@Override
	public Operator getLoginUser(String sessionId) {
		Operator operator = (Operator) cache.get(Constants.LoginUser + sessionId);
		if(null != operator)
			return operator;
		
		return null;
	}

	@Override
	public boolean isLogin(String sessionId) {
		Operator operator = (Operator) cache.get(Constants.LoginUser + sessionId);
		if(null != operator)
			return true;
		
		return false;
	}
    

}
