package org.zerock.fmt.mapper;

import java.util.List;

import org.zerock.fmt.domain.CommentDTO;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CriteriaComment;
import org.zerock.fmt.exception.DAOException;

public interface CommentMapper {
	
	// 댓글 등록
	public abstract Integer insertComment(CommentDTO commentDTO) throws DAOException;

	// 댓글 수정
	public abstract Integer updateComment(CommentDTO commentDTO) throws DAOException;
	
	// 댓글 삭제 (게시글 삭제되는 경우 댓글도 연쇄 삭제처리 - 테스트하기)
	public abstract Integer deleteComment(String cm_number) throws DAOException;
	
	// 해당 게시글의 댓글들을 출력 (등록시간순으로 5개씩 페이징 처리) 
	public abstract List<CommentVO> selectComment(CriteriaComment criteria) throws DAOException;
	
	// 댓글 개수 카운트
	
} // end interface
