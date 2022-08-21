package org.zerock.fmt.domain;

import java.sql.Timestamp;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class CommunityDTO {
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	
	
} // end class
