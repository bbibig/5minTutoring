package org.zerock.fmt.domain;

import lombok.Data;

//자주묻는 질문과 답 페이징 처리
@Data
public class CriteriaMyPage {
	private int currPage = 1;		//현재 페이지 번호
	private int amount = 6;		//각 페이지에서 보여줄 게시물의 개수
	private int pagesPerPage = 1;	//페이지 번호 목록의 길이
	
	private String user_email;
	
}// end class
