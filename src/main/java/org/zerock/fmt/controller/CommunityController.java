package org.zerock.fmt.controller;


import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.fmt.domain.CommunityDTO;
import org.zerock.fmt.domain.CommunityVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.service.CommunityService;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/community")
@Controller
public class CommunityController implements InitializingBean{
	
private CommunityService communityService;
	
	@GetMapping
	public String communityPage() throws ControllerException {
		log.trace("communityPage()invoked");
		
//		try {
//			List<CommunityVO> list = this.communityService.selectAllList();
//			
//			model.addAttribute("__LIST__", list);		
//		}catch(Exception e) {
//			throw new ControllerException(e);
//		}
		
		return "community/3-01_community";
		
	}//communityPage
	
	
	@PostMapping("/register")
	public String register(CommunityDTO dto) throws ControllerException{
		
		log.trace("regiser({}) invoked.", dto);
		
		try {
			this.communityService.create(dto);
			
			return "redirect: /commnunity";
		}catch(Exception e) {
			throw new ControllerException(e);
		}//try-catch
		
	}//register
	
	@GetMapping("/post")
	public String communityPost() {
		log.trace("3-03_communityPost <<< 커뮤니티 게시글");
		
		return "community/3-03_communityPost";
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		
	} // communityPost	
	
	
} // end class
