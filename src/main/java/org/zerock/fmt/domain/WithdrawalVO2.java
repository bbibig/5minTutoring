package org.zerock.fmt.domain;

import java.util.Date;

import lombok.Value;

@Value
public class WithdrawalVO2 {


	private String user_name;		// 사용자명
	private String user_phone;		// 핸드폰 번호
	private String bank_account;	// 은행 계좌	
	private Integer w_quantity;		// 출금 수량 (손들기)
	private Integer w_price;		// 출금액
	private Date w_date;			// 신청일
	
} // end class
