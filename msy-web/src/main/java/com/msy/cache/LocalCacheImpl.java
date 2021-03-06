package com.msy.cache;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

import com.msy.service.ICache;


public class LocalCacheImpl extends BaseCache implements ICache,Serializable
{
	private static final long serialVersionUID = -2629949555329544823L;
	private static Map<String, Object> cacheDataMap = Collections.synchronizedMap(new HashMap<String, Object>());
	private int autoScanSeconds = 60;

	public void setAutoScanSeconds(int value)
	{
		autoScanSeconds = value;
	}

	public void init()
	{
		super.init();

		log.info("Local cache object has been created.");
	}

	@Override
	public boolean isLocal()
	{
		return true;
	}

	@Override
	public boolean isAvailable()
	{
		return true;
	}

	@Override
	public boolean keyExists(String key)
	{
		if (keyIsEmpty(key))
		{
			return false;
		}
		key = super.getFirstKey(key);
		return cacheDataMap.containsKey(key);
	}

	@Override
	public boolean put(String key, Object value)
	{
		if (keyIsEmpty(key))
		{
			return false;
		}
		key = super.getFirstKey(key);
		synchronized (cacheDataMap)
		{
			cacheDataMap.put(key, value);
		}
		return true;
	}

	@Override
	public boolean put(String key, Object value, Date expireDt)
	{
		if (keyIsEmpty(key))
		{
			return false;
		}
		key = super.getFirstKey(key);
		synchronized (cacheDataMap)
		{
			cacheDataMap.put(key, new CacheObject(key, value, expireDt));
		}
		return true;
	}

	@Override
	public Object get(String key)
	{
		if (keyIsEmpty(key))
		{
			return null;
		}
		key = super.getFirstKey(key);
		Object obj = cacheDataMap.get(key);
		if (obj instanceof CacheObject)
		{
			CacheObject co = (CacheObject) obj;
			if (co.isAvailabele())
			{
				obj = co.getObject();
			} else
			{
				obj = null;
				cacheDataMap.remove(key);
			}
		}
		return obj;
	}

	@Override
	public boolean delete(String key)
	{
		if (keyIsEmpty(key))
		{
			return false;
		}
		key = super.getFirstKey(key);
		synchronized (cacheDataMap)
		{
			cacheDataMap.remove(key);
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "LocalCacheImpl [cacheDataMap=" + cacheDataMap + "]";
	}

	class ClearExpireDataTask extends TimerTask
	{
		public void run()
		{
			log.info("Clear the expired data from cache...");
			Set<String> keySet = cacheDataMap.keySet(), removeKeySet = new HashSet<String>();
			Object obj = null;
			CacheObject co = null;
			for (String key : keySet)
			{
				obj = cacheDataMap.get(key);
				if (obj instanceof CacheObject)
				{
					co = (CacheObject) obj;
					if (!co.isAvailabele())
					{
						removeKeySet.add(key);
					}
				}
			}
			if (removeKeySet.size() > 0)
			{
				for (String key : removeKeySet)
				{
					synchronized (cacheDataMap)
					{
						cacheDataMap.remove(key);
					}
					log.info("Remove object[" + key + "] from cache");
				}
			}
		}
	}
}
