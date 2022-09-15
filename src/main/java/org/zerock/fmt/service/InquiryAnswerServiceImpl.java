package org.zerock.fmt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.InquiryAnswerDTO;
import org.zerock.fmt.domain.InquiryAnswerVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.InquiryAnswerMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service
public class InquiryAnswerServiceImpl implements InquiryAnswerService {

	@Setter(onMethod_= @Autowired)
	private InquiryAnswerMapper iaMapper; 
	
	// 1:1 문의글 답변 작성 
	@Override
	public boolean createIA(InquiryAnswerDTO dto) throws ServiceException {
		log.trace("createIA() invoked");  
		
		try { return iaMapper.insertIA(dto) == 1; } 
		catch (DAOException e) { throw new ServiceException(e); }
	} // createIA

	
	// 특정 1:1 문의글 답변 조회 
	@Override
	public InquiryAnswerVO getIA(Integer iq_number) throws ServiceException {
		
		try { return iaMapper.select(iq_number); } 
		catch(Exception e) { throw new ServiceException(e); }	
	} // getIA

} // end class
