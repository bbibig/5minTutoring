package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.CommunityDTO;
import org.zerock.fmt.exception.ServiceException;



public interface CommunityService {

	//1. 커뮤니티 게시판 전체 목록 조회
	public abstract List<CommunityDTO> selectAllList() throws ServiceException;
	
	//2. 커뮤니티 게시글 조회 
	public abstract CommunityDTO read(CommunityDTO dto) throws ServiceException;
	
	//3. 커뮤니티 게시글 삭제 
	public abstract boolean remove(CommunityDTO dto) throws ServiceException;
	
	//4. 커뮤니티 게시글 생성
	public abstract boolean create(CommunityDTO dto) throws ServiceException;
	
	//5. 커뮤니티 게시글 수정
	public abstract boolean update(CommunityDTO dto) throws ServiceException;
}
