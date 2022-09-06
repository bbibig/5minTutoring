package org.zerock.fmt.domain;


import lombok.Data;


@Data
public class FileVO {

	private int f_no;
	private String fileName;
	private String uploadPath;
	private String uuid;
	private String user_email;
	
}//end class
