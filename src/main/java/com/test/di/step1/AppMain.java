package com.test.di.step1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
	
	public static void main(String[] args) {
		System.out.println("AppMain Start");
		/*
		// Non IoC 형태 
		OldMan man = new OldMan();
		man.setName("밀키스");
		man.myphone();
		*/
		
		// IoC 컨테이너를 통해서 
		// 아이유, iphone 으로 변경(step1.xml)해 주세요 
		ApplicationContext context = 
				  new ClassPathXmlApplicationContext("test/step1-scan.xml") ;
		
		DiMan man = context.getBean("man", DiMan.class);		
		man.myphone();
		
		System.out.println("AppMain End");
	}
}
