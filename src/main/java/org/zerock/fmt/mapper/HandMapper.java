package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.domain.HandVO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.HandException;

public interface HandMapper {

	
	// == select == 
	
	// 구매하기 - 전체 상품 조회
	@Select("SELECT * FROM tbl_hand")
	public abstract List<HandVO> selectAllHands() throws HandException;
	
	// 구매하기 - 상품별 조회
	@Select("SELECT * FROM tbl_hand WHERE h_number=#{h_number}")
	public abstract HandVO selectHand(@Param("h_number") Integer hNum )throws HandException;
	
	// 구매하기 - 구매 정보 페이지
	// 이름, 전화번호, 구매상품, 수량, 상품금액, 총금액
	// user_name, user_phone, h_name, b_count, h_price, b_price
//	public abstract BuyVO getPayPage(BuyVO payPage) throws HandException;
//	public abstract BuyVO getPayPage(@Param("b_number")BuyVO payPage) throws HandException;
	
	//-- test 1
	public abstract BuyDTO payPage1(@Param("user_email")String user_email, 
								   @Param("h_number") Integer h_number) throws HandException;
	
	//-- test 2
	public abstract BuyDTO payPage2(@Param("user_email") UserVO user, 
									@Param("h_number")HandVO hand)throws HandException;
	
	
	// 보유손들기 조회
	// 이메일, 손들기,
	// user_email, hands_wallet
	@Select("SELECT user_email, hands_wallet FROM tbl_user WHERE user_email=#{user_email}")
	public abstract UserVO getMyHands(@Param("user_email")String myWallet) throws DAOException;
	
	

	// == update == 
	// user_email, hands_wallet
	public abstract Integer updateMyHands(@Param("hands_wallet")Integer myWallet) throws DAOException;
	

	
}//end 
