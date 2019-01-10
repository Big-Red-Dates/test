package com.msy.mvc.resolver;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.google.gson.Gson;
import com.msy.constant.MessageCode;
import com.msy.model.Result;
import com.msy.utils.WebUtil;

public class GsmExceptionResolver extends SimpleMappingExceptionResolver
{
	private Log log = LogFactory.getLog(this.getClass().getName());

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception)
	{
		String msg = null;
		if (exception != null)
		{
			msg = exception.getMessage();
			if (msg == null)
			{
				msg = exception.toString();
			}
			log.error("System catch a exception at " + handler.getClass().getName() + " [" + msg + "]");
		}

		if (msg != null && WebUtil.isAjAxRequest(request))
		{
			response.setHeader(MessageCode.System.AJAX_REQUEST_EXCEPTION, msg);
			Result rb = new Result();
			try
			{
				response.setCharacterEncoding("UTF-8");
				response.getOutputStream().write(new Gson().toJson(rb).getBytes("UTF-8"));
				response.flushBuffer();
			} catch (IOException e)
			{
				log.info(e.getMessage());
			}
		}

		return null;
	}
}
