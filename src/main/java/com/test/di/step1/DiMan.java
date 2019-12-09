package com.test.di.step1;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller(value = "man") 
public class DiMan {
	// 각 맴버변수는 get/set 생성 (어노테이션으로 할 경우 필요 없음) 
	// 실제 구현체는 컨테이너에서 받아요....
	@Autowired
	private Phone phone;
	
	@Value(value = "핑클")
	private String name ;
	
	public void myphone() {
		System.out.println("내이름은 " + getName());
		phone.call();
	}
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	
	public Phone getPhone() {
		return phone;
	}
	
}
