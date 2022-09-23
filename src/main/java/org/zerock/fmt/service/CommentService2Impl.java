package org.zerock.fmt.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CommentVO2;
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
			
			return CommentMapper2.readComment(fb_number);
		}catch(Exception e) {
			throw new ServiceException(e);
		}//try-catch
		
	}
		

}//end class
