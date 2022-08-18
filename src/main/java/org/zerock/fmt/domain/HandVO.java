package org.zerock.fmt.domain;

import lombok.Data;
import lombok.Value;

@Value
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class HandVO {
	
	private Integer h_number;		// 상품번호
	private String h_name;			// 상품명
	private Integer h_price;		// 상품금액
	private Integer h_count;		// 상품 개수
	

	
}//end class
