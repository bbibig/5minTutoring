package org.zerock.fmt.mapper;

import org.zerock.fmt.domain.LikeDTO;
import org.zerock.fmt.exception.DAOException;


public interface LikeMapper {
	
	//좋아요 눌렀는지 안눌렀는지 
	public abstract LikeDTO findLike(LikeDTO like) throws DAOException;
	
	//좋아요 등록
	public int insertLike(LikeDTO like) throws DAOException;
	
	//좋아요 삭제
	public void deleteLike(LikeDTO like) throws DAOException;
}
