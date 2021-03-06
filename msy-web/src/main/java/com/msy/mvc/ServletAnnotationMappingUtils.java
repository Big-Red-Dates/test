package com.msy.mvc;

import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

public class ServletAnnotationMappingUtils
{
	public static boolean checkRequestMethod(RequestMethod[] methods, HttpServletRequest request)
	{
		if (ObjectUtils.isEmpty(methods))
		{
			return true;
		}
		for (RequestMethod method : methods)
		{
			if (method.name().equals(request.getMethod()))
			{
				return true;
			}
		}
		return false;
	}

	public static boolean checkParameters(String[] params, HttpServletRequest request)
	{
		if (!ObjectUtils.isEmpty(params))
		{
			for (String param : params)
			{
				int separator = param.indexOf('=');
				if (separator == -1)
				{
					if (param.startsWith("!"))
					{
						if (WebUtils.hasSubmitParameter(request, param.substring(1)))
						{
							return false;
						}
					} else if (!WebUtils.hasSubmitParameter(request, param))
						return false;
				} else
				{
					boolean negated = (separator > 0) && (param.charAt(separator - 1) == '!');
					String key = !negated ? param.substring(0, separator) : param.substring(0, separator - 1);
					String value = param.substring(separator + 1);
					boolean match = value.equals(request.getParameter(key));
					if (negated)
					{
						match = !match;
					}
					if (!match)
					{
						return false;
					}
				}
			}
		}
		return true;
	}

	public static boolean checkHeaders(String[] headers, HttpServletRequest request)
	{
		if (!ObjectUtils.isEmpty(headers))
		{
			for (String header : headers)
			{
				int separator = header.indexOf('=');
				if (separator == -1)
				{
					if (header.startsWith("!"))
					{
						if (request.getHeader(header.substring(1)) != null)
						{
							return false;
						}
					} else if (request.getHeader(header) == null)
						return false;
				} else
				{
					boolean negated = (separator > 0) && (header.charAt(separator - 1) == '!');
					String key = !negated ? header.substring(0, separator) : header.substring(0, separator - 1);
					String value = header.substring(separator + 1);
					if (isMediaTypeHeader(key))
					{
						List requestMediaTypes = MediaType.parseMediaTypes(request.getHeader(key));
						List valueMediaTypes = MediaType.parseMediaTypes(value);
						boolean found = false;
						for (Iterator valIter = valueMediaTypes.iterator(); (valIter.hasNext()) && (!found);)
						{
							MediaType valueMediaType = (MediaType) valIter.next();
							Iterator reqIter = requestMediaTypes.iterator();
							while ((reqIter.hasNext()) && (!found))
							{
								MediaType requestMediaType = (MediaType) reqIter.next();
								if (valueMediaType.includes(requestMediaType))
								{
									found = true;
								}
							}
						}

						if (negated)
						{
							found = !found;
						}
						if (!found)
							return false;
					} else
					{
						boolean match = value.equals(request.getHeader(key));
						if (negated)
						{
							match = !match;
						}
						if (!match)
						{
							return false;
						}
					}
				}
			}
		}
		return true;
	}

	private static boolean isMediaTypeHeader(String headerName)
	{
		return ("Accept".equalsIgnoreCase(headerName)) || ("Content-Type".equalsIgnoreCase(headerName));
	}
}
