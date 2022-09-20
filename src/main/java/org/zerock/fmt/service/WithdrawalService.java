package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.exception.ServiceException;

public interface WithdrawalService {

	
// 	출금 신청
	public abstract boolean createWithdrawal(WithdrawalDTO dto) throws ServiceException;
	
	
//  출금 신청 내역 전체 조회 (승인 대기) - 관리자
	public abstract List<WithdrawalVO> getAllWithdrawalList(CriteriaAdmin cri) throws ServiceException;
	
//  출금 신청 내역 전체 조회 (승인 대기) - 관리자
	public abstract List<WithdrawalVO> getAllWithdrawalOkList(CriteriaAdmin cri) throws ServiceException;	
	
//	출금 신청 내역 페이징 총 건수 - 관리자	
	public abstract int countList(Integer w_num) throws ServiceException;
	
//  승인 여부 수정 (승인 대기 / 승인 완료)
	public abstract boolean updateState(WithdrawalDTO dto) throws ServiceException;

//	손들기 개수 차감 (사용자 - 튜터)
	public abstract boolean updateHands(String user_email) throws ServiceException;

} // end interface
