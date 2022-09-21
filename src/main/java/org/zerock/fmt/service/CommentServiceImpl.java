package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CommentDTO;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CriteriaComment;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.CommentMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class CommentServiceImpl implements CommentService {
	
	@Setter(onMethod_ = @Autowired)
	private CommentMapper commentMapper;

	
	@Override
	public boolean createComment(CommentDTO commentDTO) throws ServiceException {
		log.trace("댓글 등록");

		try { return this.commentMapper.insertComment(commentDTO) == 1; } 
		catch (Exception e) { throw new ServiceException(e); }
		
	} // createComment

	@Override
	public boolean updateComment(CommentDTO commentDTO) throws ServiceException {
		log.trace("댓글 수정");

		try { return this.commentMapper.updateComment(commentDTO) == 1; } 
		catch (Exception e) { throw new ServiceException(e); }
		
	} // updateComment

	@Override
	public boolean deleteComment(String cm_number) throws ServiceException {
		log.trace("댓글 삭제");

		try { return this.commentMapper.deleteComment(cm_number) == 1; } 
		catch (Exception e) { throw new ServiceException(e); }
		
	} // deleteComment

	@Override
	public List<CommentVO> getComment(CriteriaComment criteria) throws ServiceException {
		log.trace("해당 게시글의 댓글을 출력");

		try { return this.commentMapper.selectComment(criteria); } 
		catch (Exception e) { throw new ServiceException(e); }
		
	} // getComment

	@Override
	public CommentVO getOneComment(String cm_number) throws ServiceException {
		log.trace("해당 댓글을 출력");

		try { return this.commentMapper.selectOneComment(cm_number); } 
		catch (Exception e) { throw new ServiceException(e); }
	} // getOneComment

	@Override
	public Integer commentCount(int a_number) throws ServiceException {
		log.trace("해당 댓글을 출력");

		try { return this.commentMapper.commentCount(a_number); } 
		catch (Exception e) { throw new ServiceException(e); }
	} // commentCount

} // end class
