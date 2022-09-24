package org.zerock.fmt.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CommentDTO;
import org.zerock.fmt.domain.CommentVO;
import org.zerock.fmt.domain.CommentVO2;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.CommentMapper2;

import lombok.Setter;

@Service
public class CommentService2Impl implements CommentService2 {

	@Setter(onMethod_=@Autowired)
	private CommentMapper2 CommentMapper2;
	
	@Override
	public List<CommentVO2> readComment(Integer fb_number)throws ServiceException{
		
		try {
			Objects.requireNonNull(this.CommentMapper2);
			
			return this.CommentMapper2.readComment(fb_number);
		}catch(Exception e) {
			throw new ServiceException(e);
		}//try-catch
		
	}//readComment

	@Override
	public boolean writeComment(CommentDTO dto) throws ServiceException {
		try {
			Objects.requireNonNull(this.CommentMapper2);
			
			return this.CommentMapper2.writeComment(dto) == 1;
		}catch(Exception e) {
			throw new ServiceException(e);
		}//try-catch
		
	}//writeComment

	@Override
	public boolean updateComment(CommentDTO dto) throws ServiceException {
		
		try{ 
			return this.CommentMapper2.updateComment(dto) == 1;
		}catch(DAOException e) {
			throw new ServiceException(e);
		}//try-catch
		
	}//updateComment

	@Override
	public boolean deleteComment(CommentDTO dto) throws ServiceException {
		try{ 
			return this.CommentMapper2.deleteComment(dto) == 1;
		}catch(DAOException e) {
			throw new ServiceException(e);
		}//try-catch
	}//deleteComment

	@Override
	public CommentVO2 selectComment(CommentDTO dto) throws ServiceException {
		try{ 
			return this.CommentMapper2.selectComment(dto.getCm_number());
		}catch(DAOException e) {
			throw new ServiceException(e);
		}//try-catch
	}//selectComment
		

}//end class
