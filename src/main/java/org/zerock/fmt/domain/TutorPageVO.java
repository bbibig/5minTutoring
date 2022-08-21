package org.zerock.fmt.domain;

import lombok.Value;

@Value
public class TutorPageVO {
	
	// tbl_tutor_page
	private Integer tp_number;
	private String tp_career;
	private String tp_title;	
	private String tp_comment;
	private Integer tp_accu_answer;	
	private Double tp_average;
	// tbl_user
	private String tt_subject; 
	private String user_name;
	private String tt_school;

} // end class
