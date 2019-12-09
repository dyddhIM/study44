package com.study.attach.web;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.attach.service.IAttachService;
import com.study.attach.vo.AttachVO;

@Controller
public class AttachController {
	
	@Value("#{app['file.upload.path']}")
	private String uploadPath;
	
	@Autowired
	private IAttachService attachService;
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	// @PathVariable 사용하여 url상의 경로를 변수에 할당 "/attach/download/25625"
	@RequestMapping("/attach/download/{atch_no:[0-9]{1,16}}")
	public void process(@PathVariable(name = "atch_no") int atch_no, HttpServletResponse resp) throws Exception {
		logger.debug("atch_no = {}", atch_no);
		// 서비스를 통해 첨부파일 가져오기
		AttachVO vo = attachService.selectAttach(atch_no);
		if (vo == null) {
			printMessage(resp, "해당 첨부파일이 존재하지 않습니다.");
			return;
		}
		try {
			// 파일명에 한글이 있는경우 처리
			String downloadFileName = new String(vo.getAtch_original_name().getBytes("utf-8"), "iso-8859-1");
			String absoluteFileName = uploadPath + File.separatorChar + vo.getAtch_path() + File.separatorChar
					+ vo.getAtch_file_name();
			logger.debug("downloadFileName = {}", downloadFileName);
			logger.debug("absoluteFileName = {}", absoluteFileName);
			File f = new File(absoluteFileName);
			if (!f.isFile()) {
				printMessage(resp, "해당 첨부파일이 존재하지 않습니다.");
				return;
			}
			resp.setHeader("Content-Type", "application/octet-stream;");
			resp.setHeader("Content-Disposition", "attachment;filename=\"" + downloadFileName + "\";");
			resp.setHeader("Content-Transfer-Encoding", "binary;");
			resp.setContentLength((int) f.length()); // 진행율
			resp.setHeader("Pragma", "no-cache;");
			resp.setHeader("Expires", "-1;");
			FileUtils.copyFile(f, resp.getOutputStream()); // 파일을 응답객체의 스트림으로 내보내기
			resp.getOutputStream().close();
			try {
				attachService.increaseDownloadCount(atch_no);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		} catch (IOException e) {
			resp.reset();
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
		}
	}

	// 정상적인 다운로드가 안될경우 메시지 처리
	private void printMessage(HttpServletResponse resp, String msg) throws Exception {
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-Type", "text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		// target이 지정되지 않은 경우 history.back() 으로 처리
		out.println("<script type='text/javascript'> ");
		out.println("alert('" + msg + "');		");
		out.println("self.close();		");
		out.println("</script>		");
		out.println("<h4>첨부파일 문제 " + msg + "</h4> ");
		out.println("<button onclick='self.close()'>닫기</button>");
	}
}