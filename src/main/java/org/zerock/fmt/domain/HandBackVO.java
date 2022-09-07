package org.zerock.fmt.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class HandBackVO {

	private Integer hb_number;
	private Integer qb_number;
	private Integer tb_number;
	private Date hback_date;
	private String user_email;

} // end class
