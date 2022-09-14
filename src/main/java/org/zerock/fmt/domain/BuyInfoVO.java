package org.zerock.fmt.domain;

import java.util.Date;

import lombok.Value;

// 마이페이지 - 구매내역 상세보기 용 
@Value
public class BuyInfoVO {
	
	private Integer	b_number;		// 구매번호
	private String user_email;		// 회원계정
	private Integer h_number;		// 상품번호
	private Date b_date;			// 주문일자
	private Integer b_count;		// 구매수량
	private Integer b_price;		// 총금액
	private String b_state;			// 결제상태 
	
	private String h_name;			// 상품이름 
	private String user_phone;		// 주문자 핸드폰번호 
	private String user_name;		// 주문자 이름
}//end class
