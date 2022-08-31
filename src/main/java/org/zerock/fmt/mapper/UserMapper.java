package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;

public interface UserMapper {

	//-------------- SELECT 
	//전체 회원조회 - 어드민(회원관리용)
	@Select("SELECT * FROM tbl_user")
	public abstract List<UserVO> selectAllUser() throws DAOException;
	
	//회원 정보조회 + 손들기 개수 조회
	@Select("SELECT * FROM tbl_user WHERE user_email=#{user_email}")
	public abstract UserVO selectUser(@Param("user_email") String user_email) throws DAOException;
	
	//닉네임 중복검사 
	@Select("SELECT COUNT(*) FROM tbl_user WHERE USER_NICK = #{user_nick}")
	public abstract Integer selectNicCheck(@Param("user_nick")String newNick) throws DAOException;
	
	//로그인 
	public abstract UserVO selectLogin(@Param("user_email")String user_email,
									   @Param("user_pw")String user_pw) throws DAOException;
		
	//-------------- INSERT 
	//학생 가입
	public abstract Integer insertStudent(UserDTO newStudent) throws DAOException;

	//튜터 가입 
	public abstract Integer insertTutor(UserDTO newTutor) throws DAOException;
	
	//++카카오 가입
	//++네이버 가입
	
	
	//-------------- UPDATE 
	//정보변경 - 마이페이지(정보수정용)
	public abstract Integer updateUser(UserDTO user) throws DAOException;
	
	//정보변경 - 어드민(튜터회원 승인용)
	@Update("UPDATE tbl_user SET pass='Accept' WHERE user_email=#{user_email}")
	public abstract Integer updateTutorPass(@Param("user_email")String user_email) throws DAOException;
	
	//회원탈퇴 - 마이페이지(정지)
	public abstract Integer updateUserStop(@Param("user_email")String user_email) throws DAOException;
	
	
	//-------------- 손들기 UPDATE 
	
	//손들기 구매(학생) + 획득(튜터) -> 개수 추가
	public abstract Integer updateHandGet(@Param("h_count") Integer h_count,
										@Param("user_email")String user_email) throws DAOException;
	//손들기 사용-> 개수 차감
	public abstract Integer updateHandUse(@Param("h_count") Integer h_count,
										@Param("user_email")String user_email) throws DAOException;
	
	
	
	
	//-------------- DELETE 
	//회원삭제가 아니라 정지 변경이므로 삭제SQL 없음
	
}//end 
