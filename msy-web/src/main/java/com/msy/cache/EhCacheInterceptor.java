package com.msy.cache;

import java.io.Serializable;

import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * 缓存方法拦截器核心代码 
 */
public class EhCacheInterceptor implements MethodInterceptor, InitializingBean{
	
	private static final Logger logger = Logger.getLogger(EhCacheInterceptor.class);
	
	private Cache cache;
	 
	@Override
	public void afterPropertiesSet() throws Exception {
		logger.info(cache + " A cache is required. Use setCache(Cache) to provide one.");
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
//		String targetName = invocation.getThis().getClass().getName();
//		String targetName = invocation.getThis().getClass().getSimpleName();
		String targetName = "";
        String methodName = invocation.getMethod().getName();
        Object[] arguments = invocation.getArguments();
        Object result;
 
        String cacheKey = getCacheKey(targetName, methodName, arguments);
        Element element = null;
        synchronized (this) {
            element = cache.get(cacheKey);
            if (element == null) {
                logger.info(cacheKey + "加入到缓存： " + cache.getName());
                // 调用实际的方法
                result = invocation.proceed();
                element = new Element(cacheKey, (Serializable) result);
                cache.put(element);
            } else {
                logger.info(cacheKey + "使用缓存： " + cache.getName() + " value :" + element.getValue());
            }
        }
        return element.getValue();
	}
	
	/**
     * 返回具体的方法全路径名称 参数
     * @param targetName 全路径
     * @param methodName 方法名称，必须为：getXxxxOnXxx 如：getMrchByIdOnEhCache
     * @param arguments 参数
     * @return 完整方法名称
     */
    private String getCacheKey(String targetName, String methodName, Object[] arguments) {
        StringBuffer sb = new StringBuffer();
        methodName = methodName.substring(3, methodName.lastIndexOf("On"));
        //sb.append(targetName).append(".").append(methodName);
        sb.append(methodName);
        if ((arguments != null) && (arguments.length != 0)) {
            for (int i = 0; i < arguments.length; i++) {
                sb.append("_").append(arguments[i]);
            }
        }
        return sb.toString();
    }
	
	public void setCache(Cache cache) {
        this.cache = cache;
    }

}
