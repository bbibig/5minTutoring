package org.zerock.fmt.domain;

import java.util.Date;

import lombok.Value;

@Value
public class CommentVO {
	
	private Integer cm_number;		//댓글 번호
	private Integer as_number;		//답변게시글 번호 (fk, null 허용)
	private Integer fb_number;		//커뮤니티글 번호 (fk, null 허용)
	private String user_email;		//이메일
	private String cm_content;		//댓글 내용
	private Date cm_date;			//작성일

} // end class
