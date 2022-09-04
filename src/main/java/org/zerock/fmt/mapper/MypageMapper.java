package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.QuestionBardVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.DAOException;

//마이페이지
public interface MypageMapper {
	
//	============<<SELECT>>============
	//1. 기본정보 조회(특정 회원) + 프로필사진 추가 예정 (UserMapper에 동일 기능 있음)
	@Select("SELECT * FROM tbl_user WHERE user_email = #{user_email}")
	public abstract UserVO selectUser(UserDTO dto) throws DAOException;
	
	//1-2 회원 DB 비밀번호 조회
	@Select("SELECT user_pw FROM tbl_user WHERE user_email = #{user_email}")
	public abstract String selectUserDbPw(String user_email) throws DAOException;
	
	//2-1. 나의 질문글 목록 조회 페이징 처리(내림차순으로)
	public abstract List<QuestionBardVO> selectAllMyQuestionList(CriteriaMyPage cri) throws DAOException;
	
	//2-2. 나의 질문글 목록 총 개수 조회
	@Select("SELECT count(qb_number) "
			+ "FROM tbl_question_board "
			+ "WHERE user_email = #{user_email}")
	public abstract Integer getMyQuestionTotalAmount(@Param("user_email") String user_email) throws DAOException;
	
	
	//3-1. 나의 커뮤니티 작성글 목록 조회 페이징 처리(내림차순으로)
	public abstract List<CommunityVO> selectAllMyCommunitytList(CriteriaMyPage cri) throws DAOException;
	
	//3-2. 나의 커뮤니티 작성글 목록 총 개수 조회
	@Select("SELECT count(fb_number) "
			+ "FROM tbl_community "
			+ "WHERE user_email = #{user_email}")
	public abstract Integer getMyCommunityTotalAmount(@Param("user_email") String user_email) throws DAOException;
	
	
	//4-1. 나의 댓글 목록 조회 페이징 처리(내림차순으로)
	public abstract List<CommentVO> selectAllMyCommentList(CriteriaMyPage cri) throws DAOException;
	
	//4-2. 나의 댓글 목록 총 개수 조회
	@Select("SELECT count(cm_number) "
			+ "FROM tbl_comment "
			+ "WHERE user_email = #{user_email}")
	public abstract Integer getMyCommentTotalAmount(@Param("user_email") String user_email) throws DAOException;
	
//	============<<INSERT>>============

	
//	============<<UPDATE>>============
	//1. 기본정보 수정 + 프로필사진 추가 예정(UserMapper에 동일 기능 있음)
	public abstract boolean updateUserInfo(UserDTO dto) throws DAOException;
	
	
//	============<<DELETE>>============


}// end interface
