package org.zerock.fmt.domain;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class QuestionBoardVO {
	
	private Integer qb_number;
	private Integer tp_number;		// 튜터페이지번호
	private String user_email;
	
	private String qb_title;
	private String qb_content;
	private Integer qb_answer;		// 답변유무

	private Date regdate;
	private Date updateDate;

} // end class
