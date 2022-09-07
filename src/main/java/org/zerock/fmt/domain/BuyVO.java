package org.zerock.fmt.domain;

import java.util.Date;

import lombok.Value;

@Value
public class BuyVO {
	
	private Integer	b_number;		// 구매번호
	private String user_email;		// 회원계정
	private Integer h_number;		// 상품번호
	private Date b_date;			// 주문일자
	private Integer b_count;		// 구매수량
	private Integer b_price;		// 총금액
	private String b_state;			// 결제상태 
	
	private String h_name;			// 상품이름 
	
}//end class
