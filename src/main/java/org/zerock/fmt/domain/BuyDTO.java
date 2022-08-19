package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDTO {
	
	private String user_email;		// 회원계정
	private String user_name;		// 회원명
	private String user_phone;		// 회원휴대폰번호

	
	private Integer h_number;		// 상품번호
	private String h_name;			// 상품명
	private Integer h_price;		// 상품금액
	private Integer b_count;		// 구매수량
	
}//end class
