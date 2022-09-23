package org.zerock.fmt.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class TutoringBoardDTO {
	
	private Integer tb_number;
	private Integer tp_number;
	private String user_email;
	private String tb_title;
	private String tb_content;

} // end class
