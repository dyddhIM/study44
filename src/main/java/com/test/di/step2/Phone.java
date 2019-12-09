package com.test.di.step2;

import java.util.List;
import java.util.Map;

public abstract class Phone {
    // 소유자, 주소는 생성자를 통해서 받도록 함			
	protected String owner;
	protected Map<String, String> address;
	// 장비정보는 setter 메서드를 통해 받도록강제함(abstract)		
	protected List<String> deviceInfos;

	public Phone(String owner, Map<String, String> address) {
		this.owner = owner;
		this.address = address;
	}
	

	// --- 구현자가 직접	처리해야할 추상 메서드	---
    // 전화기 정보를	콘솔에 출력	
	public abstract void info();

    // 전환번호에 해당하는 사용자명 리턴	
	public abstract String getAddressInfo(String phoneNumber);

    // 기기정보를 받는 setter 메서드	
	public abstract void setDeviceInfos(List<String> deviceInfos);

	// --- 기본적으로 제공되는 메서드	---	
    // 전화왔을때 정보 콘솔에 출력	
	public void calling(String phoneNumber) {
		String name = getAddressInfo(phoneNumber);
		System.out.println(name + "	calling");
	}

    // --- 기본적으로 제공되는 getter 메서드	---
	public String getOwner() {
		return owner;
	}

	public Map<String, String> getAddress() {
		return address;
	};
}
