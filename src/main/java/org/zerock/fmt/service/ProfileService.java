package org.zerock.fmt.service;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.ProfileDTO;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.UserProfileVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;

//프로필 사진
public interface ProfileService {
	
	//1. 프로필 사진 정보 조회
	public abstract List<ProfileVO> getProfile(String user_email) throws ServiceException;
	
	//2. 프로필 사진 등록
	public abstract boolean createProfile(ProfileDTO dto) throws ServiceException;
	
	//3. 프로필 사진 수정
	public abstract boolean modifyProfile(ProfileDTO dto) throws ServiceException;

	//4. 유저 닉네임 조회
	public abstract String getUserNick(String user_email) throws ServiceException;
	
	//5. 튜터 닉네임 조회
	public abstract String getTutorNick(Integer tp_number) throws ServiceException;
	
	//6. 튜터 이메일 조회
	public abstract String getTutorEmail(Integer tp_number) throws ServiceException;
	
	//7. 유저 닉네임, 사진정보 조회
	public abstract List<UserProfileVO> getUserNaP(String user_email) throws ServiceException;
	
}// end interface
