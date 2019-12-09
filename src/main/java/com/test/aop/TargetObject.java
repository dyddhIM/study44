package com.test.aop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.study.board.vo.BoardVO;

// 핵식 기능이 동작하는 클래스(서비스, 컨트로러 정도)

// @Component (=@Controller, @Service , @Repository), @Named
@Component("target")
public class TargetObject {
	
	// 핵심 기능 (비즈니스를 처리하는 메서드 )
	public int mySum(int a, int b) throws Exception{
		System.out.println("mysum call");
		if(a < 1) throw new Exception("숫자가 작아요");
		int sum = a + b;
		return sum;
	}
	
	public List<BoardVO> myList() throws Exception {
		List<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO v = null;
		v = new BoardVO(); v.setBo_title("대현아빠 티어는 골드");
		list.add(v);
		
		v = new BoardVO(); v.setBo_title("재성아빠 티어는 브론즈");
		list.add(v);
		
		v = new BoardVO(); v.setBo_title("동완아빠 티어는 몰라요~~");
		list.add(v);
		
		return list;
	}
	

}







