package org.zerock.fmt.domain;

import java.util.Date;
import lombok.Value;

@Value
public class QuestionBoardVO {
	
	
	private Integer qb_number;
	private Integer tp_number;		// 튜터페이지번호
	private String user_email;
	private String user_name;
	
	private String qb_title;
	private String qb_content;
	private Integer qb_answer;		// 답변유무

	private Date regdate;
	private Date updateDate;

} // end class
