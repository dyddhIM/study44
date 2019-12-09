package com.test.di.step1;

public class OldMan {
	// 이곳을 컨트롤로, 폰는 서비스로 생각하시면 됩니다.
	// 참새를 키우기로 했죠..
	// private Phone phone = new SamsungPhone();

	// 삼성에서 아이폰으로 변경이 일어났죠..
	// 근데.. 문제는 왜. 컨트롤러의 소스가 변경되어야 하냐?????	
	private Phone phone = new IPhone();
	
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
}
