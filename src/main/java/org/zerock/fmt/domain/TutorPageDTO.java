package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class TutorPageDTO {
	
	private Integer tp_number;
	private String tp_career;
	private String tp_title;	
	private String tp_comment;

} // end class
