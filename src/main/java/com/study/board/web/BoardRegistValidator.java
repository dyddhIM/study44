package com.study.board.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.study.board.vo.BoardVO;

public class BoardRegistValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// 객체 확인 
		return BoardVO.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BoardVO board = (BoardVO)target;
		if(StringUtils.isBlank(board.getBo_title())) {
			errors.rejectValue("bo_title", "required", "제목은 필수 입니다.");
		}
		if(StringUtils.isBlank(board.getBo_writer())) {
			errors.rejectValue("bo_writer", "required",  "작성자는 필수 입니다.");
		}
		
		ValidationUtils.rejectIfEmptyOrWhitespace(
				  errors, "bo_pass", "required", "패스워드는 필수 입니다.");
		ValidationUtils.rejectIfEmptyOrWhitespace(
				  errors, "bo_class", "required", "분류는 필수 입니다.");
		
		if(board.getBo_pass() == null || board.getBo_pass().length() < 4 
				                         || board.getBo_pass().length() > 20 
			 ) {
			errors.rejectValue("bo_pass", "size",  "패스워드는 4글자 이상 20글자 이하입니다.");
		}
		
	}

}







