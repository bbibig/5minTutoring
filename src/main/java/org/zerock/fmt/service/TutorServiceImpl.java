package org.zerock.fmt.service;

import java.util.List;
import java.util.Objects;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.TutorPageDTO;
import org.zerock.fmt.domain.TutorPageVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.TutorMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class TutorServiceImpl 
	implements TutorService {
	
	@Setter(onMethod_= @Autowired)
	private TutorMapper tutorMapper;
	
	@Override
	public TutorPageVO getAllTInfo(@Param("tp_number") Integer tp_number) throws ServiceException {
		log.trace("튜터페이지 전체 정보 조회");
		
		try { return this.tutorMapper.selectAllTInfo(tp_number); } 
		catch (DAOException e) { throw new ServiceException("비지니스가 예외가 발생했습니다."); }
	} // getAllTInfo

	@Override
	public List<TutorPageVO> getRecentTCard() throws ServiceException {
		log.trace("튜터카드 최신순 조회");
		
		try { return this.tutorMapper.selectRecentTCard(); } 
		catch (DAOException e) { throw new ServiceException("비지니스가 예외가 발생했습니다."); }
	} // getTCardInfo
	
	@Override
	public boolean createIntroInfo(TutorPageDTO tutorPagedto) throws ServiceException {
		log.trace("튜터 소개 입력", tutorPagedto);

		try { return this.tutorMapper.insertIntroInfo(tutorPagedto) == 1; } 
		catch (DAOException e) { throw new ServiceException("비지니스가 예외가 발생했습니다."); }
	} // insertIntroInfo

	@Override
	public boolean updateTInfo(TutorPageDTO tutorPagedto) throws ServiceException {
		log.trace("튜터 소개 수정", tutorPagedto);
		
		try { return this.tutorMapper.updateTInfo(tutorPagedto) == 1; } 
		catch (DAOException e) { throw new ServiceException("비지니스가 예외가 발생했습니다."); }
	} // updateTInfo

	
} // end class
