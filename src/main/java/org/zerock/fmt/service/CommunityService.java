package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CommunityDTO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.domain.CriteriaCommunity;
import org.zerock.fmt.domain.LikeDTO;
import org.zerock.fmt.exception.ServiceException;



public interface CommunityService {

	//1. 커뮤니티 게시판 전체 목록 조회
	public abstract List<CommunityVO> selectAllList(CriteriaCommunity page) throws ServiceException;
	
	//2. 커뮤니티 게시글 조회
	public abstract CommunityVO read(CommunityDTO dto) throws ServiceException;
	
	//3. 커뮤니티 게시글 삭제 
	public abstract boolean remove(CommunityDTO dto) throws ServiceException;
	
	//4. 커뮤니티 게시글 생성
	public abstract boolean create(CommunityDTO dto) throws ServiceException;
	
	//5. 커뮤니티 게시글 수정
	public abstract boolean update(CommunityDTO dto) throws ServiceException;
	
	//6. 커뮤니티 게시글 총 갯수
	public abstract Integer allCount(CriteriaCommunity page)throws ServiceException;
	
	//7. 커뮤니티 댓글 총 갯 수
	public abstract boolean updateCommentCount(int fb_number)throws ServiceException;
	
	//8. 좋아요 확인
//	public abstract LikeDTO findHeart(int fb_number, String user_email)throws ServiceException;
}
