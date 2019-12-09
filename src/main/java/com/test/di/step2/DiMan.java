package com.test.di.step2;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component(value = "man")
public class DiMan {

	@Value("홍길동")
	private String name;
	
	@Autowired  // (= @Resoucre, @Inject)	
	private Phone phone;
	
	@PostConstruct
	public void myInit() {
		// 해당 클래스에서 필요한 초기화 작업 
		System.out.println("태어나서 처음으로 호출되는 메서드!!");
	}
	
	@PreDestroy
	public void myClose() {
		// 해당 클래스가 종료시 필요한 작업  
		System.out.println("저 죽어요~~~!~~~");
	}
	
	
	public void myphone() {
		System.out.println( name );
		phone.info();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}
}
