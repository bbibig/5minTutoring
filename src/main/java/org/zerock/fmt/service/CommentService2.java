package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CommentVO2;
import org.zerock.fmt.exception.ServiceException;


public interface CommentService2 {

	//댓글조회
	public List<CommentVO2> readComment(Integer fb_number)throws ServiceException;
}