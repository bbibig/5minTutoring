package org.zerock.fmt.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class UseHandTVO {	//mypage에 사용
	
	private Integer tb_number;	// 질문글 번호
	private Integer tp_number;	// 과외글 번호
	private String user_email;
	private String tb_title;	//학생 이메일
	private String tb_content;
	private Date tb_date;
	private Integer tb_answer;
	
	private String tutor_nick;	//튜터 닉네임

} // end class
