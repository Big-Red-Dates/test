package com.msy.interceptor;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.msy.annotation.SessionChecking;
import com.msy.constant.Constants;
import com.msy.constant.MessageCode;
import com.msy.model.Operator;
import com.msy.model.Result;
import com.msy.service.CacheSessionService;
import com.msy.service.SessionService;
import com.msy.utils.WebUtil;


public class ControllerInterceptor implements HandlerInterceptor
{
	private Log log = LogFactory.getLog(this.getClass().getName());
	@Autowired
	private SessionService sessionService ;
	@Autowired
	private CacheSessionService cacheSessionService;
	private final String REQTIME = "_REQTIME_";
	private final String LOGINUSER = "_LOGINUSER_";
	private String noPermission = "/";
	private String noSession = "/";
	private Set<String> notCheckURISet = new HashSet<String>();
	private Set<String> notCheckSessionURISet = new HashSet<String>(); 
	public void setNoPermission(String value)
	{
		noPermission = value;
	}

	public void setNoSession(String value)
	{
		noSession = value;
	}

	public void setNotCheckURISet(Set<String> value)
	{
		notCheckURISet = value;
	}

	public void setNotCheckSessionURISet(Set<String> notCheckSessionURISet) {
		this.notCheckSessionURISet = notCheckSessionURISet;
	}

	private boolean needCheckUri(HttpServletRequest request, String requestUri)
	{
		for (String key : notCheckURISet)
		{
			key = request.getContextPath() + key;
			if (requestUri.startsWith(key))
			{
				return false;
			}
		}
		return true;
	}

	private boolean needSessionCheckUri(HttpServletRequest request, String requestUri)
	{
		for (String key : notCheckSessionURISet)
		{
			key = request.getContextPath() + key;
			log.debug(requestUri + " " + key);
			if (requestUri.contains(key))
			{
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		request.setAttribute(REQTIME, System.currentTimeMillis());

		String requestUri = request.getRequestURI();

		log.debug("requestUri =======> "+requestUri);
		
		boolean flag = needCheckUri(request, requestUri);
		if (!flag)
		{
			return true;
		}
		Operator loginUser = handleSessionChecking(request, response, handler);
		request.setAttribute(LOGINUSER, loginUser);

		if (loginUser == null)
		{
			log.info(String.format("%-16s%-6s%-10s%s", request.getRemoteAddr(), "begin", "", requestUri));
			return false;
		}
		if (loginUser != null && loginUser.getOperatorId() == null)
		{
			log.info(String.format("%-16s%-6s%-10s%s", request.getRemoteAddr(), "begin", "", requestUri));
			return true;
		}

		if (loginUser != null)
		{
			log.info(String.format("%-16s%-25s%-6s%-10s%s", request.getRemoteAddr(), loginUser.getOperatorLoginName() + " ", "begin", "", requestUri));
		}

		return true;
	}

	private Operator handleSessionChecking(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		String requestUri = request.getRequestURI();
		Operator loginUser = new Operator();

		SessionChecking sessionChecking = AnnotationUtils.findAnnotation(handler.getClass(), SessionChecking.class);
		
		boolean flag = needSessionCheckUri(request,requestUri);
		if (flag)// 需要检查Session
		{
			loginUser = cacheSessionService.getLoginUser(sessionService.getSessionId(request));
			log.debug("loginUser by session : "+loginUser);
			
			if (loginUser == null)
			{
				log.warn(String.format("%-18s%-50s%s", request.getRemoteAddr(), requestUri, "It is a no session request..."));
				if (WebUtil.isAjAxRequest(request))
				{
					response.setHeader(Constants.AJAX_REQUEST_CHECKING, MessageCode.System.AJAX_REQUEST_CHECKING_NOSESSION);
					Result rb = new Result();
					rb.setResultCode("0001");
					rb.setResultMsg("用户未登录或会话超时");
					responseResultBean(request, response, rb);
				} else
				{
					response.sendRedirect(request.getContextPath() + noSession);
				}
			}
		}
		return loginUser;
	}


	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception
	{
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception) throws Exception
	{
		log.debug("afterCompletion...");
	}

	private void responseResultBean(HttpServletRequest request, HttpServletResponse response, Result rb)
	{
		if (WebUtil.isCrossDomainRequest(request))
		{
			WebUtil.handleCrossDomainRequest(request, response, rb);
		} else
		{
			try
			{
				response.getOutputStream().write(new Gson().toJson(rb).getBytes("UTF-8"));
			} catch (Exception e)
			{
				log.error(e.getMessage());
			}
		}
	}
}
