package org.zerock.fmt.domain;

import lombok.Value;

@Value
public class TutorPageVO {
	
	private Integer tp_number;
	private String user_email;
	private String tp_career;
	private String tp_title;	
	private String tp_comment;
	private String tp_accu_answer;	// 누적 답변
	private Double tp_average;
	// 아래 2개 컬럼 테이블에 임의로 추가함..고민중..
	private String tp_subject; 	
	private String user_name;

} // end class
