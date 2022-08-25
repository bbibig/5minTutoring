package org.zerock.fmt.domain;


import lombok.Value;


@Value
public class FileVO {

	private Integer fNo;
	private String fileName;
	private String uploadPath;
	private String uuid;
	private String userEmail;
	
}//end class
