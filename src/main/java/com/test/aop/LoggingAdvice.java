package com.test.aop;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAdvice {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	// 반복적을 사용되는 포인트 컷을 별도로 설정 , 사용하는 곳은 메서드명으로 
	@Pointcut(value = "execution( * com.test.aop.*.my*(..))")
	public void mypoint() {
	}
	
	// value 에 포인트 지정 
	// @Before(value= "execution( * com.test.aop.*.my*(..))")
	@Before(value = "mypoint()")
	public void beforeLogging(JoinPoint joinPoint ) {
		 Class clazz = joinPoint.getTarget().getClass();
	     String clsName = clazz.getSimpleName();
	     String methodName = joinPoint.getSignature().getName();
	     Object[] args = joinPoint.getArgs();
	 
	     StringBuffer sb = new StringBuffer();
	     sb.append("clsName = [" + clsName + "." + methodName + "]");
	     for (int i = 0; i < args.length; i++) {
			Object object = args[i];
			sb.append("\n args " + i + "=" + ToStringBuilder.reflectionToString(object));
	     }
	     
	     logger.debug(sb.toString());	     
	}
	
	@AfterReturning(value = "mypoint()" ,returning = "ret")
	public void returnLogging(JoinPoint joinPoint , Object ret) {
	     String clsName = joinPoint.getTarget().getClass().getSimpleName();
	     String methodName = joinPoint.getSignature().getName();
	     StringBuffer sb = new StringBuffer();
	     
	     sb.append("clsName = [" + clsName + "." + methodName + "]");
	     if(ret == null) {
	    	 sb.append("\n result is null");
	     }else if(ret instanceof List) {
	    	 List l = (List)ret;
	    	 for(int i = 0; i < l.size() ; i++) {
	    		 Object o = l.get(i);
	    		 sb.append("\n[" + i + "]" + ToStringBuilder.reflectionToString(o));
	    	 }
	     }else if(ret instanceof Map) {
	    	 Map m = (Map) ret;
	    	 Iterator iter = m.keySet().iterator(); 
	    	 while(iter.hasNext() ) {
	    		 Object key = iter.next();
	    		 Object val = m.get(key);
	    		 sb.append("\n[" + key + "]" + ToStringBuilder.reflectionToString(val));
	    	 }
	     }else {
	    	 sb.append("\n" + ToStringBuilder.reflectionToString(ret));
	     }
	     
	     logger.debug(sb.toString());
	     	     
	}
	
}




