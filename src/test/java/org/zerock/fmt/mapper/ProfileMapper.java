package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.ProfileDTO;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.exception.DAOException;

//프로필 사진
public interface ProfileMapper {
	
//	============<<SELECT>>============
	//1. 프로필 사진정보 조회
	@Select("SELECT * FROM tbl_user_profile WHERE user_email = #{user_email}")
	public abstract List<ProfileVO> selectProfile(String user_email) throws DAOException;
	
//	============<<INSERT>>============
	//1. 프로필 사진 등록(조회해서 없으면 등록하기)
	public abstract boolean insertProfile(ProfileDTO dto) throws DAOException;
	
//	============<<UPDATE>>============
	//1. 프로필 사진 수정(조회해서 있으면 수정하기)
	public abstract boolean updateProfile(ProfileDTO dto) throws DAOException;
	
//	============<<DELETE>>============


}// end interface
