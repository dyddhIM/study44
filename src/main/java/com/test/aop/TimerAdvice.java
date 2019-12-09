package com.test.aop;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TimerAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// around 사용하실 경우에만 ProceedingJoinPoint 
	public Object timerCheck(ProceedingJoinPoint joinPoint) throws Throwable {		
		String clsName = joinPoint.getTarget().getClass().getSimpleName();
		String methodName = joinPoint.getSignature().getName();
		long start = System.currentTimeMillis();
		try {
			//  전처리
			Object obj = joinPoint.proceed();  // ** 핵심객체를 호출 
			logger.debug("{}.{}={}", clsName, methodName, ToStringBuilder.reflectionToString(obj));
			return obj;
		} catch (Throwable e) {			
			e.printStackTrace();
			throw e;
		}finally {
			logger.debug("{}.{} time ={}", clsName, methodName, System.currentTimeMillis() - start );
		}
	}
	
}
