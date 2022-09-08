package org.zerock.fmt.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class AnswerDTO {

	private Integer qb_number;
	private String user_email;
	private String a_content;
	
} // end class
