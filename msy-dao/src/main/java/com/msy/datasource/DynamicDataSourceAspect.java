package com.msy.datasource;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import com.msy.annotation.DataSource;


@Order(1)
@Aspect
public class DynamicDataSourceAspect {
	
	private Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);
	
	@Before("@annotation(dataSource)")
	public void annotation(JoinPoint point, DataSource dataSource) {
		String value = dataSource == null ? "" : dataSource.value();
		if(StringUtils.isNotBlank(value)) {
			DynamicDataSource.setDataSourceKey(value);
			logger.debug("DynamicDataSource[annotation]:"+dataSource.value());
		}
	}
	
	@Before("@within(dataSource)")
	public void within(JoinPoint point, DataSource dataSource) {
		String value = dataSource == null ? "" : dataSource.value();
		if(StringUtils.isNotBlank(value)) {
			DynamicDataSource.setDataSourceKey(value);
			logger.debug("DynamicDataSource[within]:"+dataSource.value());
		}
	}
	
    /**
     * 拦截目标方法，获取由@DataSource指定的数据源标识，设置到线程存储中以便切换数据源
     * 
     * @param point
     * @throws Exception
     */
	@Before("execution(* com.msy.*.mapper.*.*(..))")
    public void execution(JoinPoint point) throws Exception {
        Class<?> target = point.getTarget().getClass();
        MethodSignature signature = (MethodSignature) point.getSignature();
        // 默认使用目标类型的注解，如果没有则使用其实现接口的注解
        for (Class<?> clazz : target.getInterfaces()) {
            resolveDataSource(clazz, signature.getMethod());
        }
        resolveDataSource(target, signature.getMethod());
    }

    /**
     * 提取目标对象方法注解和类型注解中的数据源标识
     * 
     * @param clazz
     * @param method
     */
    private void resolveDataSource(Class<?> clazz, Method method) {
        try {
            Class<?>[] types = method.getParameterTypes();
            // 默认使用类型注解
            if (clazz.isAnnotationPresent(DataSource.class)) {
                DataSource dataSource = clazz.getAnnotation(DataSource.class);
                DynamicDataSource.setDataSourceKey(dataSource.value());
                logger.debug("DynamicDataSource[execution]:"+dataSource.value());
            }
            // 方法注解可以覆盖类型注解
            Method m = clazz.getMethod(method.getName(), types);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
                DataSource dataSource = m.getAnnotation(DataSource.class);
                DynamicDataSource.setDataSourceKey(dataSource.value());
                logger.debug("DynamicDataSource[execution]:"+dataSource.value());
            }
        } catch (Exception e) {
        	logger.error("resolveDataSource", e);
        }
    }
	
}
