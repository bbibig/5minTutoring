package org.zerock.fmt.domain;

import lombok.Data;

@Data
public class CriteriaAdmin {
	
	private int currPage = 1;		//현재 페이지 번호
	private int amount = 5;			//각 페이지에서 보여줄 게시물의 개수 
	private int pagesPerPage =3; 	//pagination의 길이 
	
	private String keyword;			//검색키워드
	private String type;			//검색타입
	private String approval;		//카테고리 정렬
	
	private String user_group;
	private String user_status;
//	private String[] typeArr;
//	
//	public void setType(String type) {
//		this.type = type;
//		this.typeArr = type.split("");
//	}
}
