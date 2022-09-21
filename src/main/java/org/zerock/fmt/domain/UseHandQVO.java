package org.zerock.fmt.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class UseHandQVO {	//mypage에 사용
	
	private Integer qb_number;	// 질문글 번호
	private Integer tp_number;	// 과외글 번호
	private String user_email;
	private String qb_title;	//학생 이메일
	private String qb_content;
	private Integer qb_answer;
	private Date regdate;
	private Date updatedate;
	
	private String tutor_nick;	//튜터 닉네임

} // end class
