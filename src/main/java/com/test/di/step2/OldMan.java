package com.test.di.step2;

import java.util.HashMap;
import java.util.Map;

public class OldMan {

	private String name;
	private Phone phone;
	
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
