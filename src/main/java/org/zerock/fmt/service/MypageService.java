package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.InquiryQuestionDTO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.domain.InquiryVO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.UseHandVO2;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.exception.ServiceException;

//마이페이지
public interface MypageService {
	
	//1. 기본정보 조회
	public abstract UserVO getUserInfo(UserDTO dto) throws ServiceException;
	public abstract String getUserDbPw(String user_email) throws ServiceException;
		
	//1-2 기본정보 수정 테스트
	public abstract boolean modifyUserInfo(UserDTO dto) throws ServiceException;

	
	//2-1. 나의 질문글 목록 조회 페이징 처리(내림차순으로)
	public abstract List<QuestionBoardVO> getAllMyQuestionList(CriteriaMyPage cri) throws ServiceException;
	
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
	
	// 5. 1:1 문의하기
	public abstract boolean createIQ(InquiryQuestionDTO dto) throws ServiceException;
	
	// 5-1. 나의 1:1 문의글 목록 조회 페이징 처리(내림차순으로)
	public abstract List<InquiryQuestionVO> getAllMyInquiryList(CriteriaMyPage cri) throws ServiceException;
	
	// 5-2. 나의 1:1 문의글 목록 총 개수 획득
	public abstract int getMyInquiryTotalAmount(String user_email) throws ServiceException;
	
	// 5-3. 나의 1:1 문의 & 답변 조회
	public abstract InquiryVO getMyInquiry(Integer iq_number) throws ServiceException;

	
	// 6. 손들기 출금 신청 하기 (튜터)
	public abstract boolean createWithdrawal(WithdrawalDTO dto) throws ServiceException;
	
	// 6-1. 나의 손들기 출금 신청 목록 조회 페이징 처리(내림차순으로)
	public abstract List<WithdrawalVO> getAllMyWithdrawalList(CriteriaMyPage cri) throws ServiceException;
	
	// 6-2. 나의 출금 신청 목록 총 개수 조회
	public abstract int getMyWithdrawalTotalAmount(String user_email) throws ServiceException;
	
	
	// 7. 탈퇴하기
	public abstract boolean userStatus(String user_email) throws ServiceException;
		
}// end interface
