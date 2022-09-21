package org.zerock.fmt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CommunityDTO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.domain.CriteriaCommunity;
import org.zerock.fmt.domain.LikeDTO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.CommunityMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2

@Service
public class CommunityServiceImpl implements CommunityService, InitializingBean, DisposableBean {

	@Setter(onMethod_=@Autowired)
	private CommunityMapper communityMapper;
	
	
	@Override
	public List<CommunityVO> selectAllList(CriteriaCommunity page) throws ServiceException {
		log.trace("SelectAllList() invoked.");
		try {
			Objects.requireNonNull(this.communityMapper);
			
			return communityMapper.selectAllList(page);
		}catch(Exception e) {
			throw new ServiceException(e);
		}//try-catch
		
	}
	
	@Override
	public CommunityVO read(CommunityDTO dto) throws ServiceException {
		log.trace("get() invoked");
		try {
			Objects.nonNull(this.communityMapper);
			
			return communityMapper.select(dto.getFb_number());
		}catch(Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean remove(CommunityDTO dto) throws ServiceException {
		log.trace("remove() invoked.");
		try{ 
			return communityMapper.delete(dto.getFb_number())==1;
		}catch(DAOException e) {
			throw new ServiceException(e);
		}//try-catch
	}

	@Override
	public boolean create(CommunityDTO dto) throws ServiceException {
		log.trace("create()invoked.");
		try {
			return communityMapper.insert(dto) ==1;
		}catch(DAOException e) {
			throw new ServiceException(e);
		}//try-catch
	}

	@Override
	public boolean update(CommunityDTO dto) throws ServiceException {
		log.trace("update()invoked.");
		try {
			return communityMapper.update(dto) == 1;
		}catch(DAOException e) {
			throw new ServiceException(e);
		}//try-catch
	}

	
	@Override
	public void destroy() throws Exception {			
		log.trace("destroy() invoked.");
		
	} // destroy


	
	@Override
	public void afterPropertiesSet() throws Exception {	
		log.trace("afterPropertiesSet() invoked.");
		
		// 필드에 의존성 주입이 정상적인지 체크
		Objects.requireNonNull(this.communityMapper);			
		assert this.communityMapper != null;						
		
		log.info("\t+ this.mapper: {}", this.communityMapper);
	}

	@Override
	public Integer allCount(CriteriaCommunity page) throws ServiceException {
		try {
			return this.communityMapper.allCount(page);
		}catch(DAOException e) {
			throw new ServiceException(e);
		} // try-catch
		
	}









	
}//end class
