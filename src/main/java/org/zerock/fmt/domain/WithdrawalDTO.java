package org.zerock.fmt.domain;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class WithdrawalDTO {

	private Integer w_number;		// 출금 번호
	private String user_email;
	private String bank_account;	
	private Integer w_quantity;		// 출금 수량 (손들기)
	private Integer w_price;		// 출금액
	private String approval;		// 승인여부
	private Date w_date;			// 출금신청 날짜
	
} // end class
