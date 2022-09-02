package org.zerock.fmt.domain;


import java.util.Date;

import lombok.Value;

@Value
public class InquiryQuestionVO {

	// 문의글 작성 (사용자)
	
	private Integer iq_number;		// 문의글 번호
	private String user_email;
	private String iq_title;
	private String iq_content;
	private Date iq_date;		
	private Integer iq_pass;		// 답변 유무

} // end class
