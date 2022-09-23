package org.zerock.fmt.domain;

import java.util.Date;

import lombok.Value;

@Value
public class TutoringBoardVO {
	
	private Integer tb_number;
	private Integer tp_number;
	private String user_email;
	private String user_name;
	private String tb_title;
	private String tb_content;
	private Date tb_date;
	private Integer tb_answer;

} // end class
