package org.zerock.fmt.domain;

import java.util.Date;

import lombok.Value;

@Value
public class ReviewVO {

	private Integer rv_number;		//리뷰넘버
	private Integer tp_number;		//튜터넘버
	private String user_email;		//학생
	private Integer rv_star;		//별점
	private Date rv_date;			//리뷰날짜
	private String rv_content;		//리뷰내용
	
	private String user_nick;		//학생닉네임
	


}//end class
