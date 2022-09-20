package org.zerock.fmt.domain;

import java.util.Date;

import lombok.Value;

@Value
public class WithdrawalVO {

	private Integer w_num;
	private String user_email;
	private String bank_account;
	private Integer w_quantity;		// 출금 수량 (손들기)
	private Integer w_price;		// 출금액
	private String approval;		// 승인여부 (승인 대기 / 승인 완료)
	private Date w_date;
	private Date w_okdate;	
	
} // end class
