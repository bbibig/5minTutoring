package org.zerock.fmt.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;


@Value
public class CommunityVO {
	private Integer fb_number;
	private String user_email;
	private String fb_title;
	private String fb_content;
	private String fb_comment_count;
	private String fb_like_count;
	private Date fb_date;
	
	
} // end class
