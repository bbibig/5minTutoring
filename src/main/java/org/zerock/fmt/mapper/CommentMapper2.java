package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.fmt.domain.CommentDTO;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CommentVO2;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;

public interface CommentMapper2 {
	
	
	//댓글조회
	public abstract List<CommentVO2> readComment(@Param("fb_number")Integer fb_number)throws DAOException;
	
	//댓글작성
	public abstract Integer writeComment(CommentDTO dto) throws DAOException;
	
	//댓글수정
	public abstract Integer updateComment(CommentDTO dto)throws DAOException;
	
	//댓글삭제
	public abstract Integer deleteComment(CommentDTO dto)throws DAOException;
	
	//선택된 댓글조회
	public abstract CommentVO2 selectComment(@Param("cm_number")Integer bno)throws DAOException;
	
	
}// end interface
