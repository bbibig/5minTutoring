package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProfileDTO {
	
	private Integer profile_number;
	private String user_email;
	private String file_name;
	private String file_path = "C://temp/profile/";

} // end class
