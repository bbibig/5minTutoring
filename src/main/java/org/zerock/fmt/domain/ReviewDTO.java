package org.zerock.fmt.domain;

import lombok.Data;

@Data
public class ReviewDTO {

	private Integer rv_number;		//리뷰넘버
	private Integer tp_number;		//튜터넘버
	private String user_email;		//학생
	private Integer rv_star;			//별점
	private String rv_content;		//리뷰내용

}//end class
