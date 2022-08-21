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
			+ "    /*+ index_desc(tbl_board) */\r\n"
			+ "    *\r\n"
			+ "FROM test_community\r\n"
			+ "WHERE bno>0")
	public abstract List<CommunityDTO> selectAllList() throws DAOException;
	
	//게시글 삭제
	@Delete("DELETE FROM test_community WHERE bno = #{bno}")
	public abstract Integer delete(@Param("bno")Integer bno) throws DAOException;
	
	//새로운 게시글 등록
	public abstract Integer insert(CommunityDTO dto) throws DAOException;

	//게시글 수정
	public abstract Integer update(CommunityDTO dto) throws DAOException;
	
	//게시글 선택
	@Select("SELECT bno, title, content, writer, insert_ts FROM test_community WHERE bno = #{bno}")
	public abstract CommunityDTO select(@Param("bno")Integer bno) throws DAOException;
	
	
	

	
	
}
