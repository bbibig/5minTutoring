package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.ProfileDTO;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.UserProfileVO;
import org.zerock.fmt.exception.DAOException;

//프로필 사진
public interface ProfileMapper {
	
//	============<<SELECT>>============
	//1. 프로필 사진정보 조회
	@Select("SELECT * FROM tbl_user_profile WHERE user_email = #{user_email}")
	public abstract List<ProfileVO> selectProfile(String user_email) throws DAOException;
	
	//2. 유저 닉네임 조회
	@Select("SELECT user_nick FROM tbl_user WHERE user_email = #{user_email}")
	public abstract String selectUserNick(String user_email) throws DAOException;
	
	//3. 튜터 닉네임 조회
	@Select("SELECT user_nick FROM tbl_user u, tbl_tutor_page t "
			+ "WHERE u.user_email = t.user_email AND t.tp_number = #{tp_number}")
	public abstract String selectTutorNick(Integer tp_number) throws DAOException;
	
	//4. 튜터 이메일 조회
	@Select("SELECT u.user_email FROM tbl_user u, tbl_tutor_page t "
			+ "WHERE u.user_email = t.user_email AND t.tp_number = #{tp_number}")
	public abstract String selectTutorEmail(Integer tp_number) throws DAOException;
	
	//5. 유저 닉네임, 사진정보 조회
	public abstract List<UserProfileVO> selectUserNaP(String user_email) throws DAOException;
	
	
//	============<<INSERT>>============
	//1. 프로필 사진 등록(조회해서 없으면 등록하기)
	public abstract boolean insertProfile(ProfileDTO dto) throws DAOException;
	
//	============<<UPDATE>>============
	//1. 프로필 사진 수정(조회해서 있으면 수정하기)
	public abstract boolean updateProfile(ProfileDTO dto) throws DAOException;
	
//	============<<DELETE>>============


}// end interface
