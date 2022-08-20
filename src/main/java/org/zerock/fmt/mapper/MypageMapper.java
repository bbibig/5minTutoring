package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.QuestionBardVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;

//마이페이지
public interface MypageMapper {
	
//	============<<SELECT>>============
	//1. 기본정보 조회(특정 회원) + 프로필사진 추가 예정
	@Select("SELECT * FROM tbl_user WHERE user_email = #{user_email}")
	public abstract UserVO selectUser(@Param("user_email") String user_email) throws DAOException;
	
	//2. 나의 질문글 목록 조회
	@Select("SELECT * FROM tbl_question_board WHERE user_email = #{user_email}")
	public abstract List<QuestionBardVO> selectAllQuestionList(@Param("user_email") String user_email);
	
//	============<<INSERT>>============

	
//	============<<UPDATE>>============
	//1. 기본정보 수정(학생) + 프로필사진 추가 예정
	public abstract boolean updateStudentInfo(UserDTO dto) throws DAOException;
	
	//1. 기본정보 수정(튜터) + 프로필사진 추가 예정
		public abstract boolean updateTutorInfo(UserDTO dto) throws DAOException;
	
//	============<<DELETE>>============


}// end interface
