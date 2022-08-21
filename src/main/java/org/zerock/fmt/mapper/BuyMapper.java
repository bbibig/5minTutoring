package org.zerock.fmt.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.exception.DAOException;

public interface BuyMapper {

	// SELECT
	// 구매하기 - 구매 정보 조회
	public abstract BuyDTO selectPayPage(
			@Param("user_email")String user_email, 
			@Param("h_number") Integer h_number) throws DAOException;

	// 마이페이지 - 구매내역 조회
	@Select("SELECT * FROM tbl_buy WHERE b_number=#{b_number}")
	public abstract BuyDTO selectMyPayPage(@Param("b_number") Integer bNum ) throws DAOException;
	
	
	// INSERT
	// 구매하기
	public abstract Integer insertBuyHands(BuyDTO buyHands) throws DAOException;
	
	
	

}//end 
