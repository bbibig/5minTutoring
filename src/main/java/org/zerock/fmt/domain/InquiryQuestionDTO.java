package org.zerock.fmt.domain;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor 

@Data
public class InquiryQuestionDTO {

	private Integer iq_number;		// 문의글 번호
	private String user_email;
	private String iq_title;
	private String iq_content;
	private Date iq_date;			// 문의 날짜
	private Integer iq_pass;		// 답변 유무 

} // end class
