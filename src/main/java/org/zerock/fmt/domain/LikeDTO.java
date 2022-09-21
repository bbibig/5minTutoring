package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class LikeDTO {
	
	private Integer like_number;
	private Integer fb_number;
	private String user_email;
	private Integer LikeCount;
	
}//end class
