package com.test.di.step2;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component  // 이름은 기본적은 클래스명으로 ("IPhone")
public class IPhone extends Phone {
	
	// SpEL : 스프링에서 사용하는 EL  
	
	public IPhone(@Value("홍길동")  String owner,
			      @Value("#{address}") Map<String, String> address) {
		super(owner, address);
	}

	@Override
	public void info() {
		System.out.println("----- 기기 정보 ------");
		System.out.println("기기명 : iPhoneX ");
		System.out.println("소유자명  : " + owner);
		if(deviceInfos != null) {
			for (String x : deviceInfos) {
				System.out.println(x);
			}
		}else {
			System.out.println("기기정보 없슴");
		}
		System.out.println("-----------------------");
	}

	@Override
	public String getAddressInfo(String phoneNumber) {
		if( ! address.containsKey(phoneNumber) ) {
			return null;
		}
		return address.get(phoneNumber);
	}

	@Override
	@Autowired
	public void setDeviceInfos(@Value("#{devices}") List<String> deviceInfos) {
		this.deviceInfos = deviceInfos;
	}

}
