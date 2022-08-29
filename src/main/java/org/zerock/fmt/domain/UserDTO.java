package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

	private String user_email;
	private String user_pw;
	private String user_nick;
	private String user_name;
	private String user_birth;
	private String user_gender;
	private String user_phone;
	
	private String st_school;		//학교 
	private String st_grade;		//학년

	private String tt_school;		//재학or졸업
	private String tt_subject;		//과목
	private String tt_depart;		//학과

		
}//end class
