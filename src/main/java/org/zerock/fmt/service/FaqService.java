package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.FaqVO;
import org.zerock.fmt.exception.ServiceException;

//자주묻는 질문 서비스 계층
public interface FaqService {
	
	//1. 자주묻는 질문 목록 전체 조회
	public abstract List<FaqVO> getList() throws ServiceException;

}// end interface
