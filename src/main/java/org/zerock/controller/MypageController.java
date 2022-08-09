package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/mypage")	//기본URI(Base URI)
@Controller
public class MypageController {
	
	
	@RequestMapping("/studentPage")
	public String studentPage() {
		log.trace("7-01_StudentPage");
		
		return "mypage/7-01_StudentPage";
	}// studentPage
	
	@RequestMapping("/tutorPage")
	public String tutorPage() {
		log.trace("7-02_TutorPage");
		
		return "mypage/7-02_TutorPage";
	}// tutorPage
	
	@RequestMapping("/myQuestion")
	public String myQuestion() {
		log.trace("7-03_MyQuestionList");
		
		return "mypage/7-03_MyQuestionList";
	}// myQuestion
	
	@RequestMapping("/community/write")
	public String communityWrite() {
		log.trace("7-04_CommunityW");
		
		return "mypage/7-04_CommunityW";
	}// communityWrite
	
	@RequestMapping("/community/comments")
	public String communityComments() {
		log.trace("7-05_CommunityC");
		
		return "mypage/7-05_CommunityC";
	}// communityComments
	
	@RequestMapping("/qList")
	public String qList() {
		log.trace("7-06_QList");
		
		return "mypage/7-06_QList";
	}// qList
	
	@RequestMapping("/question")
	public String question() {
		log.trace("7-07_Q");
		
		return "mypage/7-07_Q";
	}// question
	
	@RequestMapping("/qAndA")
	public String qAndA() {
		log.trace("7-07_QandA");
		
		return "mypage/7-07_QandA";
	}// qAndA
	
	@RequestMapping("/unregister")
	public String unregister() {
		log.trace("7-08_Unregister");
		
		return "mypage/7-08_Unregister";
	}// unregister
	
	@RequestMapping("/unregister/completed")
	public String unregisterCompleted() {
		log.trace("7-09_UnregisterCompleted");
		
		return "mypage/7-09_UnregisterCompleted";
	}// unregisterCompleted
	
	@RequestMapping("/studentHands/use")
	public String studentHandsUse() {
		log.trace("7-10_StudentHandsListUse");
		
		return "mypage/7-10_StudentHandsListUse";
	}// studentHandsUse
	
	@RequestMapping("/studentHands/buy")
	public String studentHandsBuy() {
		log.trace("7-11_StudentHandsListBuy");
		
		return "mypage/7-11_StudentHandsListBuy";
	}// studentHandsBuy
	
	@RequestMapping("/studentHands/buy/detail")
	public String studentHandsBuyDetail() {
		log.trace("7-12_StudentHandsListBuyD");
		
		return "mypage/7-12_StudentHandsListBuyD";
	}// studentHandsBuyDetail
	
	@RequestMapping("/tutorHands/get")
	public String tutorHandsGet() {
		log.trace("7-13_TutorHandsListGet");
		
		return "mypage/7-13_TutorHandsListGet";
	}// tutorHandsGet
	
	@RequestMapping("/tutorHands/withdraw")
	public String tutorHandsWithdraw() {
		log.trace("7-14_TutorHandsListWithdraw");
		
		return "mypage/7-14_TutorHandsListWithdraw";
	}// tutorHandsWithdraw
	
	@RequestMapping("/withdraw")
	public String withdraw() {
		log.trace("7-15_Withdraw");
		
		return "mypage/7-15_Withdraw";
	}// withdraw
	
	@RequestMapping("/withdraw/completed")
	public String withdrawCompleted() {
		log.trace("7-16_WithdrawCompleted");
		
		return "mypage/7-16_WithdrawCompleted";
	}// withdrawCompleted

}//end class