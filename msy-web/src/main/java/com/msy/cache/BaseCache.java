package com.msy.cache;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.msy.utils.AuthxUtil;


public class BaseCache implements Serializable
{
	protected Log log = LogFactory.getLog(this.getClass().getName());
	protected boolean firstKeyIsForceMd5 = false;

	public void setFirstKeyIsForceMd5(boolean value)
	{
		firstKeyIsForceMd5 = value;
	}

	public void init()
	{

	}

	protected String getFirstKey(String key)
	{
		if (firstKeyIsForceMd5)
		{
			key = AuthxUtil.encryptByMd5(key);
		}
		return key;
	}

	protected boolean keyIsEmpty(String key)
	{
		if (key == null || "".equals(key))
		{
			log.error("The key [" + key + "] is empty.");
			return true;
		}
		return false;
	}
}
