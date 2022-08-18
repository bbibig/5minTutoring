package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.FaqVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.FaqMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

//자주묻는 질문 서비스 계층
@Log4j2
@NoArgsConstructor

@Service
public class FaqServiceImpl implements FaqService {
	
	@Setter(onMethod_= @Autowired)
	private FaqMapper mapper;
	
	
	@Override
	public List<FaqVO> getList() throws ServiceException {
		log.trace("getList() 자주묻는 질문 리스트 전체 조회 서비스 메소드 호출");
		
		try { return this.mapper.selectAllList(); } 
		catch (DAOException e) { throw new ServiceException(e); }

	}// getList()

}// end class


















