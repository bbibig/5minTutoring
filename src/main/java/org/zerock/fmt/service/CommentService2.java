package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CommentDTO;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CommentVO2;
import org.zerock.fmt.exception.ServiceException;


public interface CommentService2 {

	//댓글조회
	public abstract List<CommentVO2> readComment(Integer fb_number)throws ServiceException;
	
	//댓글작성
	public abstract boolean writeComment(CommentDTO dto)throws ServiceException;
	
	//댓글수정
	public abstract boolean updateComment(CommentDTO dto)throws ServiceException;
	
	//댓글삭제
	public abstract boolean deleteComment(CommentDTO dto)throws ServiceException;
	
	//선택된 댓글조회
	public abstract CommentVO2 selectComment(CommentDTO dto)throws ServiceException;
}
