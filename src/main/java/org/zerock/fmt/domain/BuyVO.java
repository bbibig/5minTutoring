package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyVO {
	
	//구매번호 1
	//user_email 보배@
	private String user_email;		// 회원계정

	//h_number 상품번호 1
	private Integer h_number;		// 상품번호
	
	//구매일자 ---
	//구매수량 10
	//구매금액
	
}//end class
