package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.exception.DAOException;

public interface WithdrawalMapper {

	//	출금하기는 삭제(D) 없음 
	// => 마이페이지에서 출금 신청 & 목록 조회
	// => 관리자페이지에서 목록 조회 & 승인여부 수정

	
//  [C] 출금 신청
	public abstract Integer insertWithdrawal(WithdrawalDTO dto) throws DAOException;
	
	
//  [R] 출금 신청 내역 전체 조회 (이것도 내림차순으로 정렬헤야 하나)

	@Select("SELECT \r\n"
			+ "    w_num, user_email, bank_account, w_quantity, w_price, approval, w_date\r\n"
			+ "FROM tbl_withdrawal")
	public abstract List<WithdrawalVO> selectAllWithdrawalList() throws DAOException;
	
	
//  [U] 승인 여부 수정
	// xml 작성..
	public abstract Integer update(WithdrawalDTO dto) throws DAOException;
	

	
	
} // end interface
