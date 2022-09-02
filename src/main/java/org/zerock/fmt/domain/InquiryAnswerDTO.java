package org.zerock.fmt.domain;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class InquiryAnswerDTO {

	private Integer iq_number;		// 문의글 번호
	private String ad_id;
	private String ia_title;
	private String ia_content;
	private Date ia_date; 			// 답변날짜
	
} // end class
 