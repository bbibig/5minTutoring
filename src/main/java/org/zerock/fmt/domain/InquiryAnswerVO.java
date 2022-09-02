package org.zerock.fmt.domain;


import java.util.Date;

import lombok.Value;
 
@Value
public class InquiryAnswerVO {

	// 문의글 답변 (관리자)
	
	private Integer iq_number;		// 문의글 번호
	private String ad_id;			// 관리자 아이디
	private String ia_title;
	private String ia_content;
	private Date ia_date; 			// 답변날짜

	
} // end class
