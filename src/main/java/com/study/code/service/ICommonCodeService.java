package com.study.code.service;

import java.util.List;

import com.study.code.vo.CommonCodeVO;


public interface ICommonCodeService {
	/**
	 * 코드분류 (com_parent)에 따른 목록 반환<br>
	 * <code> List = selectCodeByType("HB00") </code>  
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List<CommonCodeVO> selectCodeByType(String type) throws Exception; 
}
