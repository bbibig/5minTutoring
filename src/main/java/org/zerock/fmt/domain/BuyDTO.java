package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyDTO {
	
	private String user_email;		// 회원계정
	private Integer h_number;		// 상품번호
	private Integer b_count;		// 구매수량
	private Integer b_price;		// 총 금액
	
}//end class
