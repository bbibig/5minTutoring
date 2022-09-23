package org.zerock.fmt.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class AnswerVO {

	private Integer a_number;
	private Integer qb_number;
	private String a_content;
	
	private String user_email;
	private String user_name;

	private Date regdate;
	private Date updateDate;
	
} // end class
