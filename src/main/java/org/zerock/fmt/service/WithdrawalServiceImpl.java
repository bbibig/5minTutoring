package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.exception.DAOException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.WithdrawalMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Service
public class WithdrawalServiceImpl implements WithdrawalService {

	@Setter(onMethod_= @Autowired)
	private WithdrawalMapper withdrawalMapper;
	
	// 출금 신청
	@Override
	public boolean createWithdrawal(WithdrawalDTO dto) throws ServiceException {
		log.trace("createWithdrawal() invoked");
		
		try { return withdrawalMapper.insertWithdrawal(dto) == 1; } 
		catch (DAOException e) { throw new ServiceException(e); } 
	} // createWithdrawal

	
	// 출금 신청 내역 전체 조회 (내림차순) - 관리자
	@Override
	public List<WithdrawalVO> getAllWithdrawalList(CriteriaAdmin cri) throws ServiceException {
		log.trace("getAllInquiryNList() invoked.");
		
		try { return this.withdrawalMapper.selectAllWithdrawalList(cri); } 
		catch (DAOException e) { throw new ServiceException(e); }
	} // getAllWithdrawalList

	// 출금 신청 내역 개수 - 관리자
	@Override
	public int countList(Integer w_num) throws ServiceException {
		log.trace("countList() invoked.");
		try { return this.withdrawalMapper.countList(w_num); }
		catch(Exception e) { throw new ServiceException(e); }
	} // updateState
	
	
	// 승인 여부 수정 (승인 완료 / 승인 대기)
	@Override
	public boolean updateState(WithdrawalDTO dto) throws ServiceException {
		log.trace("updateState() invoked.");
		
		try { return withdrawalMapper.updateState(dto) == 1; }
		catch (DAOException e) { throw new ServiceException(e); } 
	}




} // end class