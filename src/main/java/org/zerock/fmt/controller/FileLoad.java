package org.zerock.fmt.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.fmt.domain.FileDTO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;

@NoArgsConstructor
@Log4j2
@Component
public class FileLoad {

	public List<FileDTO> uploadFile(List<MultipartFile> files, String userEmail) {

		List<FileDTO> list = new ArrayList<FileDTO>();
		
		for (MultipartFile file : files) {

			String originalName = file.getOriginalFilename();
			Long fileSize = file.getSize();
			String fileContent = file.getContentType();
			log.info("\t + 1. originalName:" + originalName);
			log.info("\t + 2. fileSize:" + fileSize);
			log.info("\t + 3. fileContent:" + fileContent);
			// ------------- 파일정보 로그 ----------------

			// ------------- 이미지파일 체크 --------------
			File checkFile = new File(file.getOriginalFilename());
			String type = null;
			try {
				type = Files.probeContentType(checkFile.toPath());
				log.info("\t + 4. MIME TYPE: " + type);
			} catch (IOException e1) {
				e1.printStackTrace();
			} // try-catch

			if (!type.startsWith("image")) {
				list = null;
			} // if

			// ------------- 파일경로 -> 날짜별로 폴더 생성
			String uploadFolder = "c://temp/upload/";
			SimpleDateFormat sfm = new SimpleDateFormat("yyyyMMdd/");
			String today = sfm.format(new Date());

			uploadFolder += today;

			File uploadPath = new File(uploadFolder);
			if (!uploadPath.exists()) {
				uploadPath.mkdirs();
			}//if

			// ------------- DTO생성해서 값 세팅

			FileDTO fileDTO = new FileDTO();
			fileDTO.setFileName(originalName);
			fileDTO.setUploadPath(uploadFolder);
			fileDTO.setUserEmail(userEmail);

			// ------------- 파일 저장
			String uuid = UUID.randomUUID().toString();
			fileDTO.setUuid(uuid);

			originalName = uuid + "_" + originalName;
			File saveFile = new File(uploadPath, originalName);

			try {
				file.transferTo(saveFile);
				log.info("\t + 6. 업로드한 파일 저장 완료");

				// ------- thumnailFile
				File thumnailFile = new File(uploadPath, "s_" + originalName);

				BufferedImage bo_image = ImageIO.read(saveFile);
				double ratio = 3; // 비율조정
				int width = (int) (bo_image.getWidth() / ratio);
				int height = (int) (bo_image.getHeight() / ratio);

				Thumbnails.of(saveFile).size(width, height).toFile(thumnailFile);
				log.info("\t + 7. thumnailFile file create");
			} catch (Exception e) {
				e.printStackTrace();
			} // try-catch

			list.add(fileDTO);
		} // for
		return list;
	}// uploadFile
}// end class
