package com.msy.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	
	Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);
	
	private static ThreadLocal<String> dataSourceKey = new ThreadLocal<String>();
	public static void setDataSourceKey(String dataSource) {
        dataSourceKey.set(dataSource);
    }
    public static String getDataSourceKey(){
		return dataSourceKey.get();
	}

	@Override
    protected Object determineCurrentLookupKey() {
    	String key = dataSourceKey.get();
    	logger.info("==============DynamicDataSource:" + key);
    	dataSourceKey.remove();
        return key;
    }
    
}
