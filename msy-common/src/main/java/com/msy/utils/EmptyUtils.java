package com.msy.utils;

import java.util.Date;
import java.util.List;

public abstract class EmptyUtils
{

	public static boolean isEmpty(String obj)
	{
		if (null == obj || obj.trim().equals("") || "null".equals(obj.trim()))
		{
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Long obj)
	{
		if (null == obj || obj.longValue() == 0)
		{
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Integer obj)
	{
		if (null == obj || obj.intValue() == 0)
		{
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Date obj)
	{
		if (null == obj)
		{
			return true;
		}
		return false;
	}

	@SuppressWarnings({ "rawtypes" })
	public static boolean isEmpty(List obj)
	{
		if (null == obj || 0 == obj.size())
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * 描述: 
	 * @author  zhengqy
	 * @date  2014年6月20日
	 * @param objs
	 * @return
	 */
	public static boolean isEmpty(final Object[] objs) {
		if (null == objs || 0 == objs.length) {
			
			return true;
		}
		
		return false;
	}

}
