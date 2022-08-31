package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.zerock.fmt.domain.CommunityDTO;
import org.zerock.fmt.exception.DAOException;

public interface CommunityMapper {

	
	//게시글 전체 목록 나열
	@Select("SELECT \r\n"
			+ "    /*+ index_desc(tbl_community) */\r\n"
			+ "    *\r\n"
			+ "FROM tbl_community"
			)
	public abstract List<CommunityDTO> selectAllList() throws DAOException;
	
	//게시글 삭제
	@Delete("DELETE FROM tbl_community WHERE fb_number = #{fb_number}")
	public abstract Integer delete(@Param("fb_number")Integer bno) throws DAOException;
	
	//새로운 게시글 등록
	public abstract Integer insert(CommunityDTO dto) throws DAOException;
	public abstract Integer insertSelectKey(CommunityDTO dto) throws DAOException;

	//게시글 수정
	public abstract Integer update(CommunityDTO dto) throws DAOException;
	
	//게시글 선택
	@Select("SELECT bno, title, content, writer, insert_ts FROM tbl_community WHERE fb_number = #{fb_number}")
	public abstract CommunityDTO select(@Param("fb_number")Integer bno) throws DAOException;
	
	
	

	
	
}
