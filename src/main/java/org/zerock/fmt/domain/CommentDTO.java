package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class CommentDTO {

	private Integer cm_number;
	private Integer a_number;
	private Integer fb_number;
	private String user_email;
	private String cm_content;
	
} // end class
