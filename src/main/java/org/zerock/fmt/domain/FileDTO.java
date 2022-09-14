package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class FileDTO {

	private String fileName;
	private String uploadPath;
	private String uuid;
	private String userEmail;
	
}//end class
