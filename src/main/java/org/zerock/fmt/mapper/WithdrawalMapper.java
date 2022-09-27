package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.domain.WithdrawalVO2;
import org.zerock.fmt.exception.DAOException;

public interface WithdrawalMapper {

	//	출금하기는 삭제(D) 없음 
	// => 관리자페이지에서 목록 조회 & 승인여부 수정

//  [C]  출금 신청
	public abstract Integer insertWithdrawal(WithdrawalDTO dto) throws DAOException;
	
//	- 관리자
//  [R]  출금 신청 내역 전체 조회
	public abstract List<WithdrawalVO> selectAllWithdrawalList(CriteriaAdmin cri) throws DAOException;
	
//	[R] 페이징 총 건수 
	public abstract int countList(CriteriaAdmin Cri) throws DAOException;
	
//	[R] 출금 신청 정보 조회
	public abstract WithdrawalVO2 selectWithdrawal(Integer w_num) throws DAOException;

//  [U]  승인 여부 수정
	public abstract Integer updateState(WithdrawalDTO dto) throws DAOException;
	
//  [U]  손들기 개수 차감 (사용자 - 튜터)
//	public abstract Integer updateHands(@Param("user_email") String user_email) throws DAOException;

//	[R] 승인 여부 별 금액
	public abstract int totalDrowal(CriteriaAdmin cri) throws DAOException;
	
} // end interface
