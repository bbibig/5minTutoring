package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.InquiryQuestionDTO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.domain.InquiryVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.InquiryQuestionMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;


@Log4j2
@NoArgsConstructor

@Service
public class InquiryQuestionServiceImpl implements InquiryQuestionService {

	@Setter(onMethod_= @Autowired)
	private InquiryQuestionMapper iqMapper;
	
	
	// 1:1 문의글 작성 
	@Override
	public boolean createIQ(InquiryQuestionDTO dto) throws ServiceException {
		log.trace("createIQ() invoked"); 
		
		try { return iqMapper.insertIQ(dto) == 1; }
		catch (DAOException e) { throw new ServiceException(e); }
	} // createIQ

	
	// 1:1 문의글 목록 조회 - 답변 완료
	@Override
	public List<InquiryQuestionVO> getAllInquiryYList(CriteriaAdmin cri) throws ServiceException {
		log.trace("getAllInquiryYList() invoked.");
		
		try { return this.iqMapper.selectAllInquiryYList(cri); } 
		catch (DAOException e) { throw new ServiceException(e); }
	} // getAllInquiryYList
	
	// 1:1 문의글 목록 조회 - 미답변
	@Override
	public List<InquiryQuestionVO> getAllInquiryNList(CriteriaAdmin cri) throws ServiceException {
		log.trace("getAllInquiryNList() invoked.");
		
		try { return this.iqMapper.selectAllInquiryNList(cri); } 
		catch (DAOException e) { throw new ServiceException(e); }
	} // getAllInquiryNList

	// 페이징 처리
	@Override
	public int countList(String iq_pass) throws ServiceException{
		log.trace("countList() invoked.");
		try { return this.iqMapper.countList(iq_pass);
		}catch(Exception e){ throw new ServiceException(e);}
	}//countList
	
	// 특정 1:1 문의글 조회 
	@Override
	public InquiryQuestionVO getInquiry(Integer iq_number) throws ServiceException {
		log.trace("getInquiry() invoked.");
		
		try { return iqMapper.selectInquiry(iq_number); } 
		catch(Exception e) { throw new ServiceException(e); }	
	} // getInquiry 

	
	// 답변 상태 수정 (답변 대기 / 답변 완료)
	@Override
	public boolean updateInquiryState(Integer iq_number) throws ServiceException {
		log.trace("updateInquiryState() invoked.");
		
		try { return iqMapper.updateInquiryState(iq_number) == 1; }
		catch (DAOException e) { throw new ServiceException(e); }
	} // updateInquiryState

} // end class
