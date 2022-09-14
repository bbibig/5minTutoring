package org.zerock.fmt.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.zerock.fmt.domain.FileDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.service.FileService;
import org.zerock.fmt.uuid.UUIDGenerator;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/tutor")
@Controller
public class CKEditorController {
	
	@Setter(onMethod_= @Autowired)
	private FileService fileService;
	
	@PostMapping("/upload/img")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, 
			MultipartHttpServletRequest multiFile, @RequestParam MultipartFile upload) throws ControllerException {
		
		log.trace("이미지 업로드");
		
		// 파일 경로 설정
		String path = "C:/temp/upload/img/";	
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd/");
		String fileDate = sdf.format(date);
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		try{
	    	// 랜덤 문자 생성
	    	String uuid = UUIDGenerator.generateUniqueKeysWithUUIDAndMessageDigest();
    	
    		String fileName = upload.getOriginalFilename();
    		byte[] bytes = upload.getBytes();
    		
    		// 날짜별 폴더 생성
    		path += fileDate;
    		File folder = new File(path);
    		log.info("path:"+ path);	
    		
    		// 파일 이름 설정
    		String ckUploadPath = path + uuid + "_" + fileName;
    		
    		// 해당 폴더 없을 경우 생성
    		if(!folder.exists()){
    			try{ folder.mkdirs(); }
    			catch(Exception e){ e.getStackTrace(); } // try-catch
	    	} // if
	    	
	    	// outputStram의 데이터 전송하고 초기화
	    	out = new FileOutputStream(new File(ckUploadPath));
	    	out.write(bytes);
	    	out.flush(); 
	    	
	    	String callback = request.getParameter("CKEditorFuncNum");
	    	printWriter = response.getWriter();
	    	
	    	String fileUrl = "upload/submit?uuid=" + uuid + "&fileDate=" + fileDate + "&fileName=" + fileName; 
	    	
	    	// 업로드시 메시지 출력
	    	printWriter.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
	    	printWriter.flush();
	    	
	    	// tbl_tt_file 테이블에 파일 정보 저장
			HttpSession session = request.getSession();
			UserVO userVO = (UserVO) session.getAttribute("__LOGIN_USER__");
			String userEmail = userVO.getUser_email();
			
			FileDTO fileDTO = new FileDTO(fileName, path, uuid, userEmail);
			try {
				int result = this.fileService.createFiles(fileDTO);
				log.info("result:" + result);
			} catch (Exception e) { throw new ControllerException(e); }
		
    	}catch(IOException | NoSuchAlgorithmException e){
    		e.printStackTrace();
    	} finally {
    		try {
    		if(out != null) { out.close(); }
    		if(printWriter != null) { printWriter.close(); }
    	} catch(IOException e) { e.printStackTrace(); }
    	} // try-catch-finally
		
    	return;
    	
    } // imageUpload
	
	
    @RequestMapping("upload/submit")
    public void ckSubmit(@RequestParam(value="uuid") String uuid
    		, @RequestParam(value="fileName") String fileName, @RequestParam(value="fileDate") String fileDate
    		, HttpServletRequest request, HttpServletResponse response) throws IOException, ControllerException {
    	
    	log.trace("서버로 전송된 이미지 출력");
    	
    	// 저장된 이미지 경로
    	String path = "C:/temp/upload/img/";
    	path += fileDate;
    	log.info("path:"+ path);
    	String sDirPath = path + uuid + "_" + fileName;
    	
    	File imgFile = new File(sDirPath);
    	
    	// 파일 없는 경우 빈 이미지 파일로 설정
    	if(imgFile.isFile()){
    		byte[] buf = new byte[1024];
    		int readByte = 0;
    		int length = 0;
    		byte[] imgBuf = null;
    		
    		FileInputStream fileInputStream = null;
    		ByteArrayOutputStream outputStream = null;
    		ServletOutputStream out = null;
    		
    		try{
    			fileInputStream = new FileInputStream(imgFile);
    			outputStream = new ByteArrayOutputStream();
    			out = response.getOutputStream();
    			
    			while((readByte = fileInputStream.read(buf)) != -1){
    				outputStream.write(buf, 0, readByte); 
    			} // while
    			
    			imgBuf = outputStream.toByteArray();
    			length = imgBuf.length;
    			out.write(imgBuf, 0, length);
    			out.flush();
    			
    		}catch(IOException e){
    			e.printStackTrace();
    		}finally {
    			outputStream.close();
    			fileInputStream.close();
    			out.close();
    		} // try-catch-finally
    	} // if
    } // ckSubmit
	
} // end class
