package org.zerock.fmt.domain;

import lombok.Value;

@Value
public class BuyVO {
	
	private Integer b_number;		// 구매번호
	private String user_name;		// 회원명
	private String user_phone;		// 회원휴대폰번호
	private String user_email;		// 회원계정
	private Integer h_number;		// 상품번호
	private String h_name;			// 상품명
	private Integer h_price;		// 상품금액
	private Integer b_count;		// 구매수량
	private Integer b_price;		// 총 구매금액
	

	
}//end class
