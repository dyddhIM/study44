package com.test.di.step1;

import org.springframework.stereotype.Service;

@Service
public class SamsungPhone implements Phone {
	
	
	@Override
	public void call() {
		System.out.println("삼성폰 전화왔유~~");
	}
	
}
