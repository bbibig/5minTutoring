package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileVO {
	
	private String user_nick;
	private Integer profile_number;

} // end class
