package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.HandVO;
import org.zerock.fmt.exception.DAOException;

public interface HandMapper {


	// SELECT
	// 구매하기 - 전체 상품 조회
	@Select("SELECT * FROM tbl_hand")
	public abstract List<HandVO> selectAllHands() throws DAOException;
	
	// 구매하기 - 상품별 조회
	@Select("SELECT * FROM tbl_hand WHERE h_number=#{h_number}")
	public abstract HandVO selectHand(@Param("h_number")Integer hNum)throws DAOException;
	
	
} // end class
