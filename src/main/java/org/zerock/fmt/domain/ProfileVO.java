package org.zerock.fmt.domain;

import lombok.Value;

@Value
public class ProfileVO {
	
	private Integer profile_number;
	private String user_email;
	private String file_name;
	private String file_path;

} // end class
