package com.msy.service;

import java.util.Date;

public interface ICache
{
	public boolean isLocal();

	public boolean isAvailable();

	public boolean keyExists(String key);

	public boolean put(String key, Object value);

	public boolean put(String key, Object value, Date expireDt);

	public Object get(String key);

	public boolean delete(String key);

}
