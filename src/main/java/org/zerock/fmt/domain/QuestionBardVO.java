package org.zerock.fmt.domain;


import java.util.Date;

import lombok.Value;

@Value
public class QuestionBardVO {
	
	private Integer qb_number;
	private String ps_kind;			//유료분류
	private Integer tp_number;		//튜터페이지번호
	
	private String user_email;
	private String qb_title;
	private String qb_content;
	private Date qb_date;
	private Integer qb_answer;		//답변유무

} // end class
