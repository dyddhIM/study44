package com.study.common.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.study.attach.vo.AttachVO;

@Component
public class StudyAttachUtils {
	
	@Value("#{app['file.upload.path']}")
	private String uploadPath;

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public AttachVO getAttachByMultipart(MultipartFile multipart, String category, String path) throws IOException {
		if (!multipart.isEmpty()) {
			// 저장할 파일명은 : 랜덤하게, 날짜시간, 원 파일명 등 기준으로 하시면 됩니다.
			String save_name = UUID.randomUUID().toString();
			AttachVO vo = new AttachVO();
			vo.setAtch_original_name(multipart.getOriginalFilename());
			vo.setAtch_file_size(multipart.getSize());
			vo.setAtch_content_type(multipart.getContentType());
			vo.setAtch_file_name(save_name);
			vo.setAtch_category(category);
			vo.setAtch_path(path);
			vo.setAtch_fancy_size(StudyFileUtils.fancySize(multipart.getSize()));
			String absoluteFileName = uploadPath + File.separatorChar + vo.getAtch_path() + File.separatorChar
					+ vo.getAtch_file_name();
			logger.debug("absoluteFileName = {}", absoluteFileName);
			// transferTo 디렉토리가 존재하지 않으면 오류 , commons-io 사용
            // multipart.transferTo(new File(absoluteFileName));
			FileUtils.copyInputStreamToFile(multipart.getInputStream(), new File(absoluteFileName));
			return vo;
		} else {
			return null;
		}
	}

	public List<AttachVO> getAttachListByMultiparts(MultipartFile[] multipartFiles, String category, String path)
			throws IOException {
		List<AttachVO> atchList = new ArrayList<AttachVO>();
		for (int i = 0; i < multipartFiles.length; i++) {
			MultipartFile multipart = multipartFiles[i];
			AttachVO vo = this.getAttachByMultipart(multipart, category, path);
			if (vo != null) {
				atchList.add(vo);
			}
		}
		return atchList;
	}
}