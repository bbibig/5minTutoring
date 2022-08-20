package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class TutorPageDTO {
	
	private Integer tp_number;
	private String user_email;
	private String tp_career;
	private String tp_title;	
	private String tp_comment;
	private Integer tp_accu_answer;	// 누적 답변
	private Double tp_average;

	private String tp_subject; 	
	private String user_name;

} // end class
