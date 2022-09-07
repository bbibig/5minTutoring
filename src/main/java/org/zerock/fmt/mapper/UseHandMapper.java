package org.zerock.fmt.mapper;

import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.HandBackVO;
import org.zerock.fmt.domain.UseHandVO;
import org.zerock.fmt.exception.DAOException;

public interface UseHandMapper {

	// 손들기 사용 정보 저장
	public abstract Integer insertUseHand(UseHandVO useHandVO) throws DAOException;

	// 손들기 반환 정보 저장
	public abstract Integer insertHandBack(HandBackVO handBackVO) throws DAOException;

	// 손들기 개수 조회
	@Select("SELECT hands_wallet FROM tbl_user WHERE user_email = #{user_email}")
	public abstract Integer selectHandsWallet(String user_email) throws DAOException;
	
} // end interface
