package org.zerock.fmt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.fmt.domain.CommentVO2;
import org.zerock.fmt.exception.DAOException;

public interface CommentMapper2 {
	
	
	//댓글조회
	public List<CommentVO2> readComment(@Param("fb_number")Integer fb_number)throws DAOException;
	
	
	
	
}// end interface