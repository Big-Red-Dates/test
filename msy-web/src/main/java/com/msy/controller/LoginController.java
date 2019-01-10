package com.msy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.msy.annotation.SessionChecking;
import com.msy.constant.Constants;
import com.msy.model.Result;
import com.msy.service.CacheSessionService;
import com.msy.service.SessionService;
import com.msy.service.SysService;


@Controller
@RequestMapping("/api/out/")
@SessionChecking(isCheck = false)
public class LoginController {

	private static final Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private SysService sysService;
	
	@Autowired
	private SessionService sessionService ;
	
	@Autowired
	private CacheSessionService cacheSessionService;
	
	@ResponseBody 
	@RequestMapping(value = "login")
	public Result login(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "password", required = true) String password,@RequestParam(value = "userType", required = true) String userType)
	{
		logger.debug(code + " login by "+password);
		Result result = new Result();
		result = this.sysService.txLogin(request, response, code, password,userType);
		return result;
	} 
	
	@ResponseBody 
	@RequestMapping(value = "logout")
	public Result loginout(HttpServletRequest request, HttpServletResponse response)
	{
		Result result = new Result();
		try{
			String sessionId = sessionService.getSessionId(request);
			
			boolean flag = this.cacheSessionService.txLogout(request, response, sessionId);
			
			if(!flag){
				result.setResultCode(Constants.RESULT_SUCCESS);
				result.setResultMsg("退出异常");
				return result;
			}
		}catch(Exception e){
			e.printStackTrace();
			logger.error("退出异常", e);
			result.setResultCode(Constants.RESULT_SUCCESS);
			result.setResultMsg("退出异常");
			return result;
		}
		
		return result;
	} 
	
}
