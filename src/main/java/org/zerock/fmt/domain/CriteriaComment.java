package org.zerock.fmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor

@Data
public class CriteriaComment {

	private int a_number;
	private int currPage;		// 현재 페이지 번호
	private int amount;		// 각 페이지에서 보여줄 댓글의 개수
	
} // end class
