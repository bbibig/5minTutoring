package org.zerock.fmt.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class UseHandVO2 {
	
	private Integer use_number;
	private Integer qb_number;	// 질문글 번호
	private Integer tb_number;	// 과외글 번호
	private Date use_date;
	private String user_email;	//학생 이메일
	
	private String tutor_email;	//튜터 이메일

} // end class
