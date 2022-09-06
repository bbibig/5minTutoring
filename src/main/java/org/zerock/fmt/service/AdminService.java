package org.zerock.fmt.service;

import java.util.List;

import org.zerock.fmt.domain.AdminVO;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.exception.ServiceException;

public interface AdminService {

	public abstract AdminVO Login(String ad_id, String ad_pw) throws ServiceException;
	
	public abstract List<AdminVO> adminList(CriteriaAdmin cri) throws ServiceException;
	
	public abstract int adminCount() throws ServiceException;
	
	
}//end interface
