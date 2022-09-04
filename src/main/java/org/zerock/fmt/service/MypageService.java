package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.QuestionBardVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ServiceException;

//마이페이지
public interface MypageService {
	
	//1. 기본정보 조회
	public abstract UserVO getUserInfo(UserDTO dto) throws ServiceException;
	public abstract String getUserDbPw(String user_email) throws ServiceException;
		
	//1-2 기본정보 수정 테스트
	public abstract boolean modifyUserInfo(UserDTO dto) throws ServiceException;

	
	//2-1. 나의 질문글 목록 조회 페이징 처리(내림차순으로)
	public abstract List<QuestionBardVO> getAllMyQuestionList(CriteriaMyPage cri) throws ServiceException;
	
	//2-2. 나의 질문글 목록 총 개수 획득
	public abstract int getMyQuestionTotalAmount(String user_email) throws ServiceException;
	
	
	//3-1. 나의 커뮤니티 작성글 목록 조회 페이징 처리(내림차순으로)
	public abstract List<CommunityVO> getAllMyCommunityList(CriteriaMyPage cri) throws ServiceException;
	
	//3-2. 나의 커뮤니티 작성글 목록 총 개수 획득
	public abstract int getMyCommunityTotalAmount(String user_email) throws ServiceException;
	
	
	//4-1. 나의 댓글 목록 조회 페이징 처리(내림차순으로)
	public abstract List<CommentVO> getAllMyCommentList(CriteriaMyPage cri) throws ServiceException;
	
	//4-2. 나의 질문글 목록 총 개수 획득
	public abstract int getMyCommentTotalAmount(String user_email) throws ServiceException;
	
}// end interface
