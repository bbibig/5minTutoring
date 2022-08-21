package org.zerock.fmt.controller;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/community")
@Controller
public class CommunityController implements InitializingBean{

	
	@GetMapping
	public String communityPage() {
		log.trace("communityPage()invoked");
		
		return "community/3-01_community";
		
	}//communityPage
	
	
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
