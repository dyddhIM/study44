package com.test.di.step2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.test.di.step2.DiMan;

public class AppMain {

	public static void main(String[] args) {
		System.out.println("--- AppMain Start ---");
		/*
		// Non IoC/Di
		OldMan man = new OldMan();
		man.setName("밀키스");  // *      
		
		Map<String, String> addr = new HashMap<String, String>();
		addr.put("042-719-8850", "넥스트");
		addr.put("119", "화재신고");
		List<String> device = new ArrayList<String>();
		device.add("Color : white");
		device.add("Price : 1,000,000");		
		
		Phone phone = new IPhone("밀키스", addr);  // *  
		phone.setDeviceInfos(device);    // * 
		
		man.setPhone(phone); // *     
		man.myphone();
		*/
		// 용권이 아빠 : 위 장비정보를 설정한 것처럼 step2.xml에 장비 정보를 입력해 주세요 
		// String config = "test/step2.xml";
		// "test/step2-simple.xml"; 
		String config = "test/step2-scan.xml"; 		
		AbstractApplicationContext context = 
				  new ClassPathXmlApplicationContext(config) ;
		
		DiMan man = context.getBean("man", DiMan.class);
		System.out.println(man.getPhone().getAddressInfo("119"));
		man.myphone();
		System.out.println("man " + man.hashCode());
		
		DiMan man2 = context.getBean("man", DiMan.class);
		System.out.println(man2.getPhone().getAddressInfo("114"));
		System.out.println("man2 " + man2.hashCode());
		
		
		context.close();
		System.out.println("--- AppMain End ---");
	}
	
	
}
