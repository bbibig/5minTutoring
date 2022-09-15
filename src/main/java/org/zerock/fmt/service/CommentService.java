package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CommentDTO;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;

public interface CommentService {

	// 댓글 등록
	public abstract boolean createComment(CommentDTO commentDTO) throws ServiceException;

	// 댓글 수정
	public abstract boolean updateComment(CommentDTO commentDTO) throws ServiceException;
	
	// 댓글 삭제 (게시글 삭제되는 경우 댓글도 연쇄 삭제처리 - 테스트하기)
	public abstract boolean deleteComment(String cm_number) throws ServiceException;
	
	// 해당 게시글의 댓글들을 출력 (등록시간순) 
	public abstract List<CommentVO> selectComment(String a_number) throws ServiceException;
	
} // end interface
