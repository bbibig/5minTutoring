package org.zerock.fmt.mapper;

import org.apache.ibatis.annotations.Param;
import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.exception.DAOException;

public interface BuyMapper {

	// 구매하기 - 구매 정보 페이지
	public abstract BuyDTO payPage(
			@Param("user_email")String user_email, 
			@Param("h_number") Integer h_number) throws DAOException;

}//end 
