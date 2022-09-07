package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.domain.BuyInfoVO;
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.exception.DAOException;

public interface BuyMapper {

	// SELECT
	// 마이페이지 - 구매내역조회 
	public abstract List<BuyVO> myPageAllBuy(CriteriaMyPage cri) throws DAOException;
	
	// 마이페이지 - 구매내역 총 수량
	@Select("SELECT count(*) FROM tbl_buy where user_email=#{user_email}")
	public abstract int myPageBuyCount(@Param("user_email")String user_email) throws DAOException;
	
	// 마이페이지 - 구매내역 상제 조회
	public abstract BuyInfoVO selectBuyDetail(Integer b_number) throws DAOException;
	
	
	
	// 어드민 - 구매내역조회
	public abstract List<BuyVO> selectAllBuy(CriteriaAdmin cri) throws DAOException;
	
	// 어드민 - 구매내역 총 개수 
	@Select("SELECT count(*) FROM tbl_buy")
	public abstract int countAllBuy() throws DAOException;
	
	// 어드민 - 총 판매 금액
	@Select("SELECT SUM(B_PRICE) FROM TBL_BUY")
	public abstract int selectAllSale() throws DAOException;
	
	
	
	// INSERT
	// 구매하기
	public abstract Integer insertBuyHands(BuyDTO buyHands) throws DAOException;
	
	
	

}//end 
