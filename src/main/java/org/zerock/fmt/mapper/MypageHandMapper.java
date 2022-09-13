package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.UseHandVO2;
import org.zerock.fmt.exception.DAOException;

//마이페이지(손들기 조회)
public interface MypageHandMapper {
	
//	============<<SELECT>>============
	//1-1. 손들기 사용 목록 조회 페이징 처리(학생)
	public abstract List<UseHandVO2> selectAllmyUsehandList(CriteriaMyPage cri) throws DAOException;
	
	//1-2. 손들기 사용 목록 총 횟수 조회
	@Select ("SELECT count(use_number) "
			+ "FROM tbl_usehand_student "
			+ "WHERE user_email = #{user_email}")
	public abstract Integer getMyUsehandTotalAmount(@Param("user_email") String user_email) throws DAOException;

}// end interface
