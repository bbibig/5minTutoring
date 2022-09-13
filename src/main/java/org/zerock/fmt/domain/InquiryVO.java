package org.zerock.fmt.domain;


import java.util.Date;

import lombok.Value;

@Value
public class InquiryVO {

	// 문의글
	
	private Integer iq_number;		// 문의글 번호
	private String user_email;
	private String iq_title;
	private String iq_content;
	private Date iq_date;		

	// 답변글
	private String ia_title;
	private String ia_content;
	private Date ia_date; 			// 답변날짜
	

} // end class
