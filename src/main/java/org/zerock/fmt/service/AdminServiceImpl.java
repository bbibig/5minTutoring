package org.zerock.fmt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.fmt.domain.AdminVO;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.mapper.AdminMapper;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@Service
public class AdminServiceImpl implements AdminService{

	@Setter(onMethod_ = @Autowired)
	private AdminMapper admin;
	
	@Setter(onMethod_ = @Autowired)
	private UserService userService;
	
	@Override
	public AdminVO Login(String ad_id, String ad_pw) throws ServiceException {
		log.trace("Admin Login ready.");
	
		try {
			AdminVO admin = this.admin.LoginAdmin(ad_id, ad_pw);
			log.info("\t + LoginAdmin : {}", admin);
			return admin;
		} catch (Exception e) { throw new ServiceException(e); }//try-catch
	}

	@Override
	public List<AdminVO> adminList(CriteriaAdmin cri) throws ServiceException {
		log.trace("AdminList");
		try {
			List<AdminVO> list = this.admin.selectAdmin(cri);
			list.forEach(log::info);
			return list;
		}catch(Exception e) { throw new ServiceException(e); }//try-catch
		
	}//login

	
	@Override
	public int adminCount() throws ServiceException {
		log.trace("adminCount");
		try {
			return this.admin.adminCount();
			}catch(Exception e) {throw new ServiceException(e);}//try-catch
	}//adminCount


	

}//end class
