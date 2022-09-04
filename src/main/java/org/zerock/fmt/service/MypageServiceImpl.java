package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.QuestionBardVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.MypageMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

//마이페이지
@Log4j2
@NoArgsConstructor

@Service
public class MypageServiceImpl implements MypageService {
	
	@Setter(onMethod_= @Autowired)
	private MypageMapper mapper;


	//1. 기본정보 조회
	@Override
	public UserVO getUserInfo(UserDTO dto) throws ServiceException {
		log.trace("getUserInfo() 기본정보 조회");
		
		try { return this.mapper.selectUser(dto); } 
		catch (DAOException e) { throw new ServiceException(e); }

	}// getList()
	
	@Override
	public String getUserDbPw(String user_email) throws ServiceException {
		log.trace("회원 DB 비밀번호 조회");
		
		try { return this.mapper.selectUserDbPw(user_email); }
		catch (DAOException e) { throw new ServiceException(e); }
	}// 회원 DB 비밀번호 조회
	
	
	//1-2 기본정보 수정 테스트
	@Override
	public boolean modifyUserInfo(UserDTO dto) throws ServiceException {
		log.trace("modifyUserInfo() 기본정보 수정");
		
		try { return this.mapper.updateUserInfo(dto); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// modifyUserInfo()
	
	
	//2-1. 나의 질문글 목록 조회 페이징 처리(내림차순으로)
	@Override
	public List<QuestionBardVO> getAllMyQuestionList(CriteriaMyPage cri) throws ServiceException {
		log.trace("getAllMyQuestionList() 나의 질문글 목록 조회");
		
		try { return this.mapper.selectAllMyQuestionList(cri); }
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getAllMyQuestionList

	//2-2. 나의 질문글 목록 총 개수 획득
	@Override
	public int getMyQuestionTotalAmount(String user_email) throws ServiceException {
		log.trace("getMyQuestionTotalAmount() 나의 질문글 목록 총 개수 조회");
		
		try { return this.mapper.getMyQuestionTotalAmount(user_email); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getMyQuestionTotalAmount
	
	
	//3-1. 나의 커뮤니티 작성글 목록 조회 페이징 처리(내림차순으로)
	@Override
	public List<CommunityVO> getAllMyCommunityList(CriteriaMyPage cri) throws ServiceException {
		log.trace("getAllMyQuestionList() 나의 커뮤니티 작성글 목록 조회");
		
		try { return this.mapper.selectAllMyCommunitytList(cri); }
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getAllMyCommunityList

	//3-2. 나의 커뮤니티 작성글 목록 총 개수 획득
	@Override
	public int getMyCommunityTotalAmount(String user_email) throws ServiceException {
		log.trace("getMyCommunityTotalAmount() 나의 커뮤니티 작성글 총 개수 조회");
		
		try { return this.mapper.getMyCommunityTotalAmount(user_email); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getMyCommunityTotalAmount
	
	
	//4-1. 나의 댓글 목록 조회 페이징 처리(내림차순으로)
	@Override
	public List<CommentVO> getAllMyCommentList(CriteriaMyPage cri) throws ServiceException {
		log.trace("getAllMyQuestionList() 나의 댓글 목록 조회");
		
		try { return this.mapper.selectAllMyCommentList(cri); }
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getAllMyCommentList

	//4-2. 나의 댓글 목록 총 개수 획득
	@Override
	public int getMyCommentTotalAmount(String user_email) throws ServiceException {
		log.trace("getMyQuestionTotalAmount() 나의 댓글 총 개수 조회");
		
		try { return this.mapper.getMyCommentTotalAmount(user_email); } 
		catch (DAOException e) { throw new ServiceException(e); }
		
	}// getMyQuestionTotalAmount
	
}// end class


















