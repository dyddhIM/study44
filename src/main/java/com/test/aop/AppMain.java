package com.test.aop;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.board.vo.BoardVO;

public class AppMain {

	public static void main(String[] args) throws Exception {
		System.out.println("--- AppMain Start ---");
		// String config = "test/aop4Xml.xml"; 
		String config = "test/aop4AspectJ.xml";
		AbstractApplicationContext context = 
				  new ClassPathXmlApplicationContext(config) ;
		
		TargetObject my = context.getBean("target", TargetObject.class);
		int result = my.mySum(10, 23);
		System.out.println("result : " + result);
		
		List<BoardVO> r = my.myList();
		System.out.println("r.size=" + r.size());
		
		context.close();
		System.out.println("--- AppMain End ---");
	}
	
	
}
