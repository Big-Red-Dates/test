package com.msy.utils;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.context.request.ServletWebRequest;

import com.google.gson.Gson;
import com.msy.constant.Constants;
import com.msy.model.Result;

public class WebUtil
{

	private static Log log = LogFactory.getLog("WebUtil");
	
	public static boolean isAjAxRequest(HttpServletRequest request)
	{
		boolean isAjaxRequest = false;
		String requestType = request.getHeader("X-Requested-With");
		String accept = request.getHeader("Accept");
		String contentType = request.getHeader("Content-Type");
		if ((requestType != null && "XMLHttpRequest".equalsIgnoreCase(requestType))
				&& ((accept != null && accept.indexOf("application/json") != -1) || (contentType != null && contentType.indexOf("application/json") != -1)) || isCrossDomainRequest(request))
		{
			isAjaxRequest = true;
		}
		return isAjaxRequest;
	}

	public static boolean isCrossDomainRequest(HttpServletRequest request)
	{
		boolean isCrossDomainRequest = false;
		String callback = request.getParameter(Constants.CROSS_DOMAIN_PARAMERNAME);
		if (!EmptyUtils.isEmpty(callback))
		{
			isCrossDomainRequest = true;
		}
		return isCrossDomainRequest;
	}

	public static boolean isCrossDomainRequest(ServletWebRequest request)
	{
		boolean isCrossDomainRequest = false;
		String callback = request.getParameter(Constants.CROSS_DOMAIN_PARAMERNAME);
		if (!EmptyUtils.isEmpty(callback))
		{
			isCrossDomainRequest = true;
		}
		return isCrossDomainRequest;
	}

	public static void handleCrossDomainRequest(HttpServletRequest request, HttpServletResponse response, Result rb)
	{
		try
		{
			String callback = request.getParameter(Constants.CROSS_DOMAIN_PARAMERNAME);
			callback += "(" + new Gson().toJson(rb) + ")";
			response.setContentType("text/script");
			response.setCharacterEncoding("UTF-8");
			response.getOutputStream().write(callback.getBytes("UTF-8"));
		} catch (Exception e)
		{
			log.error(e.getMessage());
		}
	}

	public static void handleCrossDomainRequest(ServletWebRequest request, ServletServerHttpResponse resp, Object returnValue)
	{
		try
		{
			String callback = request.getParameter(Constants.CROSS_DOMAIN_PARAMERNAME);
			callback += "(" + new Gson().toJson((Result) returnValue) + ")";
			HttpServletResponse response = resp.getServletResponse();
			response.setContentType("text/script");
			response.setCharacterEncoding("UTF-8");
			response.getOutputStream().write(callback.getBytes("UTF-8"));
		} catch (Exception e)
		{
			log.error(e.getMessage());
		}
	}

	public static Map<String, String> getRequestHeadersMap(HttpServletRequest request)
	{
		Map<String, String> map = new TreeMap<String, String>();
		Enumeration<String> e = request.getHeaderNames();
		String header = null;
		while (e.hasMoreElements())
		{
			header = e.nextElement();
			map.put(header, request.getHeader(header));
		}
		return map;
	}

	public static String getRequestHeaders(HttpServletRequest request)
	{
		Map<String, String> map = getRequestHeadersMap(request);
		StringBuffer sb = new StringBuffer();
		for (String key : map.keySet())
		{
			if (sb.length() > 0)
			{
				sb.append("<br/>");
			}
			sb.append(String.format("%-18s%1s%s", key, ":", map.get(key)));
		}
		map = null;
		return sb.toString();
	}

	public static String requestParametersToString(HttpServletRequest request)
	{
		StringBuffer sb = new StringBuffer();
		Map map = request.getParameterMap();
		if (map != null)
		{
			Iterator<String> it = map.keySet().iterator();
			boolean isOk = false;
			String key = null;
			Object value = null;
			while (it.hasNext())
			{
				key = it.next();
				value = request.getParameter(key);
				if (value != null && !value.equals("") && !key.startsWith("button.") && !key.startsWith("submitDateTime") && !key.equals("_"))
				{
					if (isOk)
					{
						sb.append("&");
					}
					sb.append(key + "=" + value);
					isOk = true;
				}
			}
		}
		return sb.toString();
	}

	/**
	 * 
	 * 描述: contextPath 例如: http://ipAddr:port/appName
	 * @param request
	 * @return
	 */
	public static String getContextPath(final HttpServletRequest request)
	{
		// request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

	/**
	 * 
	 * 描述: contextPath 例如: http://ipAddr:port
	 * 
	 * @param request
	 * @return
	 */
	public static String getDomain(final HttpServletRequest request)
	{
		// request.getServerName() + ":" + request.getServerPort() + request.getContextPath()
		return "http://" + request.getServerName() + ":" + request.getServerPort();
	}

}
