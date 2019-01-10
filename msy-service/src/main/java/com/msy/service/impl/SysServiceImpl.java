package com.msy.service.impl;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msy.constant.Constants;
import com.msy.mapper.OperatorMapper;
import com.msy.model.Operator;
import com.msy.model.Result;
import com.msy.service.CacheSessionService;
import com.msy.service.SessionService;
import com.msy.service.SysService;
import com.msy.utils.AuthxUtil;

@Service
public class SysServiceImpl implements SysService {

	private static final Logger logger = Logger.getLogger(SysServiceImpl.class);
	
	@Autowired
	private OperatorMapper operatorMapper;
	@Autowired
	private SessionService sessionService;
	@Autowired
	private CacheSessionService cacheSessionService;
	
	@Override
	public Result txLogin(HttpServletRequest request, HttpServletResponse response, String code, String password,String userType) {
		logger.debug("session:"+this.sessionService+" cacheSessionService:"+this.cacheSessionService + " operatorMapper:"+operatorMapper);
		Operator user = new Operator();
		user.setOperatorLoginName(code);
		user.setUserType(userType);
		Operator operator = this.operatorMapper.selectByNameAndType(user);
		
		Result rb = new Result();
		
		if (operator == null) {
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("用户名不正确或用户处于暂停状态");
			return rb;
		}
		if (!password.equals(operator.getOperatorLoginPassword())) {
			
			rb.setResultCode(Constants.RESULT_FAIL);
			rb.setResultMsg("密码不正确");
			return rb;
		}
		
		String sessionId = AuthxUtil.encryptByMd5(UUID.randomUUID()
				+ request.getSession().getId());
		
		cacheSessionService.txLogin(request,response,sessionId, operator);
		
		
		request.getSession(true).setAttribute(
				Constants.DefaultSessionIdName, sessionId);
		sessionService.addCookie(request, response,
				Constants.DefaultSessionIdName, sessionId);// web使用
		response.addHeader(Constants.DefaultSessionIdName,
				sessionId);// cs使用


		rb.setResultData(operator);

		return rb;
		
	}

	@Override
	public Result txLogout(HttpServletRequest request) {
		return null;
	}

}
