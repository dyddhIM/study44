package com.study.attach.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.attach.dao.IAttachDao;
import com.study.attach.vo.AttachVO;

@Service
public class AttachServiceImpl implements IAttachService {
	
	@Autowired
	private IAttachDao attachDao;

	@Override
	public AttachVO selectAttach(int atch_no) throws Exception {
		return attachDao.selectAttach(atch_no);
	}

	@Override
	public int increaseDownloadCount(int atch_no) throws Exception {
		return attachDao.increaseDownloadCount(atch_no);
	}
}