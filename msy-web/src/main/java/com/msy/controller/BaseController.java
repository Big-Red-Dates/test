package com.msy.controller;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.msy.annotation.SessionChecking;
import com.msy.model.Operator;
import com.msy.service.CacheSessionService;
import com.msy.service.SessionService;
import com.msy.utils.DateTimeUtil;



/**
 * @description:
 * @date:
 */

// isCheck=true则表示需要检查Session和权限，=false则不需要
@SessionChecking(isCheck = true)
public class BaseController
{
	protected Log log = LogFactory.getLog(this.getClass().getName());
	@Resource
	protected SessionService sessionService;
	@Resource
	protected CacheSessionService cacheSessionService;

	public Operator getLoginUser(HttpServletRequest request)
	{
		return cacheSessionService.getLoginUser(sessionService.getSessionId(request));
	}

	public String getUserName(HttpServletRequest request)
	{
		return getLoginUser(request).getOperatorLoginNick();
	}

	/**
	 * 1. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder)
	{
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
		{
			@Override
			public void setAsText(String text)
			{
				setValue(DateTimeUtil.parseStandardDate(text));
			}
		});
	}
}