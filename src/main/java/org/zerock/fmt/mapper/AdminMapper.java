package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.AdminVO;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.exception.DAOException;

public interface AdminMapper {
	
	//관리자 로그인
	public abstract AdminVO LoginAdmin(@Param("ad_id")String ad_id, @Param("ad_pw")String ad_pw) throws DAOException;
	
	//관리자 전체조회 
	public abstract List<AdminVO> selectAdmin(CriteriaAdmin cri) throws DAOException;
	@Select("SELECT count(*) FROM tbl_admin")
	public abstract int adminCount()throws DAOException;
	
}//end interface
