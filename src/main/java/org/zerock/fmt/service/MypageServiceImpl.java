package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CommentVO3;
import org.zerock.fmt.domain.CommunityVO2;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.InquiryQuestionDTO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.domain.InquiryVO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.MypageMapper;
import org.zerock.fmt.mapper.UserMapper;

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

	@Setter(onMethod_= @Autowired)
	private UserMapper userMapper;
	
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
	
	
	//1-2 기본정보 수정
	@Override
	public boolean modifyUserInfo(UserDTO dto) throws ServiceException {
		log.trace("modifyUserInfo() 기본정보 수정");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		try { 
			dto.setUser_pw(encoder.encode(dto.getUser_pw()));
			return this.mapper.updateUserInfo(dto);
		} catch (DAOException e) { throw new ServiceException(e); }// try-catch
		
	}// modifyUserInfo()
	
	
	//2-1. 나의 질문글 목록 조회 페이징 처리(내림차순으로)
	@Override
	public List<QuestionBoardVO> getAllMyQuestionList(CriteriaMyPage cri) throws ServiceException {
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
	public List<CommunityVO2> getAllMyCommunityList(CriteriaMyPage cri) throws ServiceException {
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
	public List<CommentVO3> getAllMyCommentList(CriteriaMyPage cri) throws ServiceException {
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
	
	// 5. 1:1 문의하기
	@Override
	public boolean createIQ(InquiryQuestionDTO dto) throws ServiceException {
		log.trace("createIQ() 문의하기");
		
		try { return mapper.insertIQ(dto) == 1; }
		catch (DAOException e) { throw new ServiceException(e); }
	} // createIQ

	// 5-1. 나의 1:1 문의글 목록 조회 페이징 처리(내림차순으로)
	@Override
	public List<InquiryQuestionVO> getAllMyInquiryList(CriteriaMyPage cri) throws ServiceException {
			log.trace("getAllMyInquiryList() 나의 문의글 목록 조회");
			
			try { return this.mapper.selectAllMyInquiryList(cri); }
			catch (DAOException e) { throw new ServiceException(e); }
	} // getAllMyInquiryList
	
	// 5-2. 나의 1:1 문의글 목록 총 개수 획득
	@Override
	public int getMyInquiryTotalAmount(String user_email) throws ServiceException {
		log.trace("getMyInquiryTotalAmount() 나의 문의글 총 개수 조회");
		
		try { return this.mapper.getMyInquiryTotalAmount(user_email); } 
		catch (DAOException e) { throw new ServiceException(e); }
	} // getMyInquiryTotalAmount
	
	// 5-3. 나의 1:1 문의 & 답변 조회
	@Override
	public InquiryVO getMyInquiry(Integer iq_number) throws ServiceException {
		log.trace("getMyInquiry() 나의 문의글과 답변조회");
		
		try { return mapper.selectMyInquiry(iq_number);
		} catch(Exception e) { throw new ServiceException(e); }
			
	} // getMyInquiry

	
	// 6. 손들기 출금 신청 하기 (튜터)
	@Override
	public boolean createWithdrawal(WithdrawalDTO dto) throws ServiceException {
		log.trace("createWithdrawal() 출금 신청 하기");
		
		try { 
			mapper.insertWithdrawal(dto);
			int hands = dto.getW_quantity();
			String user = dto.getUser_email();
			
			return this.userMapper.updateHandUse(hands, user) == 1;
		}
		catch (DAOException e) { throw new ServiceException(e); }
	} // createWithdrawal

	// 6-1. 나의 손들기 출금 신청 목록 조회 페이징 처리(내림차순으로)
	@Override
	public List<WithdrawalVO> getAllMyWithdrawalList(CriteriaMyPage cri) throws ServiceException {
		log.trace("getAllMyWithdrawalList() 나의 출금 신청 목록 조회");
		
		try { return this.mapper.selectAllMyWithdrawalList(cri); }
		catch (DAOException e) { throw new ServiceException(e); }
	} // getAllMyWithdrawalList

	// 6-2. 나의 출금 신청 목록 총 개수 조회
	@Override
	public int getMyWithdrawalTotalAmount(String user_email) throws ServiceException {
		log.trace("getMyWithdrawalTotalAmount() 나의 출금 신청 목록 총 개수 조회");
		
		try { return this.mapper.getMyWithdrawalTotalAmount(user_email); } 
		catch (DAOException e) { throw new ServiceException(e); }
	} // getMyWithdrawalTotalAmount
	
	
	// 7. 탈퇴하기
//	@Override
//	public boolean userStatus(String user_email) throws ServiceException {
//		log.trace("userStatus() 회원 탈퇴하기");
//		
//		try { return this.mapper.updateUserStop(user_email)==1; }
//		catch(Exception e) { throw new ServiceException(e); }
//	} // userStatus
	
}// end class


















