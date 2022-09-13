package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.UseHandVO2;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;

//마이페이지(손들기 조회)
public interface MypageHandService {
	
	//1-1. 손들기 사용 목록 조회 페이징 처리(학생)
	public abstract List<UseHandVO2> getAllMyUsehandtList(CriteriaMyPage cri) throws ServiceException;
	
	//1-2. 손들기 사용 목록 총 횟수 조회
	public abstract int getMyUsehandTotalAmount(String user_email) throws ServiceException;
	
}// end interface
