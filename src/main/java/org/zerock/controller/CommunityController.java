package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/community")
@Controller
public class CommunityController {

	
	@RequestMapping("")
	public String communityPage() {
		log.trace("communityPage()invoked");
		
		return "community/3-01_community";
	}//communityPage
	
	
	@RequestMapping("/post")
	public String communityPost() {
		log.trace("3-03_communityPost <<< 커뮤니티 게시글");
		
		return "community/3-03_communityPost";
	} // communityPost
	
	
} // end class
