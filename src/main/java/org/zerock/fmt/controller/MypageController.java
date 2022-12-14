package org.zerock.fmt.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.fmt.common.SharedScopeKeys;
import org.zerock.fmt.domain.AnswerVO2;
import org.zerock.fmt.domain.BuyInfoVO;
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CommentVO3;
import org.zerock.fmt.domain.CommunityVO2;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.InquiryAnswerVO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.domain.PageMyPageDTO;
import org.zerock.fmt.domain.ProfileDTO;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.UseHandQVO;
import org.zerock.fmt.domain.UseHandTVO;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.domain.WithdrawalDTO;
import org.zerock.fmt.domain.WithdrawalVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.InquiryAnswerService;
import org.zerock.fmt.service.InquiryQuestionService;
import org.zerock.fmt.service.MypageHandService;
import org.zerock.fmt.service.MypageService;
import org.zerock.fmt.service.ProfileService;
import org.zerock.fmt.service.UserService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/mypage")	//??????URI(Base URI)
@Controller
public class MypageController {
	
	@Setter(onMethod_= @Autowired)
	private UserService userService;
	
	@Setter(onMethod_= @Autowired)
	private MypageService mypageService;
	
	@Setter(onMethod_= @Autowired)
	private MypageHandService mypageHandService;
	
	@Setter(onMethod_= @Autowired)
	private ProfileLoad profileUpload;
	
	@Setter(onMethod_= @Autowired)
	private ProfileService profileService;
	
	@Setter(onMethod_= @Autowired)
	private InquiryQuestionService iqService;
	
	@Setter(onMethod_= @Autowired)
	private InquiryAnswerService iaService;
	
	
//============================================================	
	
//=====????????????===============================================	
	@GetMapping("/studentPage")
	public String studentPage(Model model, HttpSession session) throws ControllerException{
		log.trace("??????????????? ???????????? ??????(??????)");
		
		try {
			//1. ???????????? ??????
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			log.info("1. session scope ??????: {}", vo);
			
			UserVO userInfo = this.userService.getUserInfo(vo.getUser_email());
			model.addAttribute("_USERINFO_", userInfo);
			
			return "mypage/7-01_StudentPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// ??????????????????(??????)
	
	@PostMapping("/pwCheck")
	@ResponseBody
	public int pwCheck(@RequestParam String user_pw, HttpSession session) throws ControllerException {
		
		UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		try {
			String dbPw = this.mypageService.getUserDbPw(vo.getUser_email());
			log.info("\t+ user_pw: {}", user_pw);
			int result;
					
			if(encoder.matches(user_pw, dbPw)) { 
				log.info("???????????? ??????");
				result = 1;
			} else { log.info("???????????? ?????????"); result = 0; }// if-else

			return result;
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
	}// ???????????? ????????? ??????(ajax??? ??????)
	
	@PostMapping("/studentPageModify")
	public String studentPage(UserDTO dto, @RequestParam String user_newPw, MultipartFile file_name,
			RedirectAttributes rttrs, HttpSession session) throws ControllerException{
		log.trace("??????????????? ???????????? ??????(??????)");
		log.info("\t+ 1. file_name: {}", file_name);
		
		try {
			log.info("\t+ user_newPw: {}", user_newPw);
			dto.setUser_pw(user_newPw);
			
			if(file_name.getSize() != 0) {	//input file??? ?????? ?????? ?????????
				//1-1. ????????? ?????? ?????? ??????
				List<ProfileVO> profileVo = this.profileService.getProfile(dto.getUser_email());
				
				ProfileDTO profileDto = new ProfileDTO();
				profileDto.setUser_email(dto.getUser_email());
				profileDto.setFile_name(dto.getUser_nick() + "_profile");
				
				//1-2. DB??? ?????? ?????? ?????? ??????
				if(profileVo.size() == 0) { //DB??? ????????? ????????? ?????????, 
					this.profileService.createProfile(profileDto);
					session.setAttribute(SharedScopeKeys.USER_PROFILE, "true");
					log.info("????????? ?????? DB ??????");
				} else { 
					this.profileService.modifyProfile(profileDto);
					log.info("????????? ?????? DB ??????");
				}// if- else
				
				//1-3. ????????? ??????
				profileUpload.uploadProfile(file_name, dto);
				log.info("????????? ?????? ?????? ????????? ????????? ??????");
			}//if
			
			//2. ???????????? ??????
			if(this.mypageService.modifyUserInfo(dto)) {
				UserVO vo = this.userService.getUserInfo(dto.getUser_email());
				session.setAttribute(SharedScopeKeys.LOGIN_USER, vo);
				
				rttrs.addFlashAttribute("_USERMODIFYRESULT_", "???????????? ?????? ??????");
			}// if
			return "redirect:/mypage/studentPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// ???????????? ??????(??????)
	
	
	@GetMapping("/tutorPage")
	public String tutorPage(Model model, HttpSession session) throws ControllerException{
		log.trace("??????????????? ???????????? ??????(??????)");
		
		try {
			//1. ???????????? ??????
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			log.info("1. session scope ??????: {}", vo);
			
			UserVO userInfo = this.userService.getUserInfo(vo.getUser_email());
			model.addAttribute("_USERINFO_", userInfo);
			
			return "mypage/7-02_TutorPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
	}// ???????????? ??????(??????)
	
	@PostMapping("/tutorPageModify")
	public String tutorPageModify(UserDTO dto, @RequestParam String user_newPw, MultipartFile file_name,
			RedirectAttributes rttrs, HttpSession session) throws ControllerException{
		log.trace("??????????????? ???????????? ??????(??????)");
		log.info("\t+ 1. file_name: {}", file_name);
		
		try {
			log.info("\t+ user_newPw: {}", user_newPw);
			dto.setUser_pw(user_newPw);
			
			if(file_name.getSize() != 0) {	//input file??? ?????? ?????? ?????????
				//1-1. ????????? ?????? ?????? ??????
				List<ProfileVO> profileVo = this.profileService.getProfile(dto.getUser_email());
				
				ProfileDTO profileDto = new ProfileDTO();
				profileDto.setUser_email(dto.getUser_email());
				profileDto.setFile_name(dto.getUser_nick() + "_profile");
				
				//1-2. DB??? ?????? ?????? ?????? ??????
				if(profileVo.size() == 0) { //DB??? ????????? ????????? ?????????, 
					this.profileService.createProfile(profileDto); 
					session.setAttribute(SharedScopeKeys.USER_PROFILE, "true");
					log.info("????????? ?????? DB ??????");
				} else { 
					this.profileService.modifyProfile(profileDto);
					log.info("????????? ?????? DB ??????");
				}// if- else
				
				//1-3. ????????? ??????
				profileUpload.uploadProfile(file_name, dto);
				log.info("????????? ?????? ?????? ????????? ????????? ??????");
			}//if
			
			//2. ???????????? ??????
			if(this.mypageService.modifyUserInfo(dto)) {
				UserVO vo = this.userService.getUserInfo(dto.getUser_email());
				session.setAttribute(SharedScopeKeys.LOGIN_USER, vo);
				
				rttrs.addFlashAttribute("_USERMODIFYRESULT_", "???????????? ?????? ??????");
			}// if
			return "redirect:/mypage/tutorPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// ???????????? ??????(??????)
	
//=====?????? ?????????===============================================
	@GetMapping("/myQuestion")
	public String myQuestion(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("??????????????? ?????? ????????? ?????? ??????");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<QuestionBoardVO> list = this.mypageService.getAllMyQuestionList(cri);
			model.addAttribute("_MYQLIST_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageService.getMyQuestionTotalAmount(vo.getUser_email()));
			model.addAttribute("_MYQLISTPAGENATION_", pageDto);
						
			return "mypage/7-03_MyQuestionList";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// ?????? ????????? ??????

//=====?????? ????????????===============================================
	@GetMapping("/community/write")		//GET	
	public String communityWrite(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("??????????????? ?????? ???????????? ????????? ?????? ??????");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<CommunityVO2> list = this.mypageService.getAllMyCommunityList(cri);
			model.addAttribute("_MYCOMMUNITY_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageService.getMyCommunityTotalAmount(vo.getUser_email()));
			model.addAttribute("_MYCOMMUNITYPAGENATION_", pageDto);
						
			return "mypage/7-04_CommunityW";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// ?????? ?????????
	
	@GetMapping("/community/comments")		//GET	
	public String communityComments(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("??????????????? ?????? ?????? ?????? ??????");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<CommentVO3> list = this.mypageService.getAllMyCommentList(cri);
			model.addAttribute("_MYCOMMENT_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageService.getMyCommentTotalAmount(vo.getUser_email()));
			model.addAttribute("_MYCOMMENTPAGENATION_", pageDto);
						
			return "mypage/7-05_CommunityC";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// ?????? ?????? ??????
	
	
//===== ?????? 1:1 ?????? ===============================================	
	@GetMapping("/qList")	// GET
	public String qList(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("??????????????? ?????? ?????? ?????? ??????");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<InquiryQuestionVO> list = this.mypageService.getAllMyInquiryList(cri);
			model.addAttribute("_MYINQUIRY_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageService.getMyInquiryTotalAmount(vo.getUser_email()));
			model.addAttribute("_MYINQUIRYPAGENATION_", pageDto);
						
			return "mypage/7-06_QList";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// ?????? ?????? ?????? ??????	
	
	
	@GetMapping("/qAndA")	// GET
	public String qAndA(@RequestParam Integer iq_number,CriteriaMyPage cri, Model model) throws ControllerException {
		log.trace("??????????????? ????????? ????????? ?????? ??????");
		log.info(iq_number); 
		
		try {
			// ??????
			InquiryQuestionVO Questionvo = this.iqService.getInquiry(iq_number);
			model.addAttribute("_INQUIRYQUESTION_", Questionvo);	
			model.addAttribute("_CURRENTPAGE_", cri);
			log.info("\t+ Questionvo: {} " + Questionvo);
			
			// ??????
			InquiryAnswerVO Answervo = this.iaService.getIA(iq_number);
			model.addAttribute("_INQUIRYANSWER_",Answervo);
			log.info("\t+ Answervo: {}" + Answervo);
			
			return "mypage/7-07_QandA";	
		} catch(Exception e) { throw new ControllerException(e);} // try-catch
		
	} // ????????? ??????&?????? ??????
	
//===== ?????? ?????? ===============================================		
	@GetMapping("/unregister")	
	public String unregister(Model model, HttpSession session) throws ControllerException {
		log.trace("?????? ?????? ?????????");
		
		return "mypage/7-08_Unregister";
		
	} // ?????? ?????? ?????????
	
	@PostMapping("/unregisterConfirm")	// POST
	@ResponseBody
	public int unregisterConfirm(@RequestParam String user_pw, Model model, HttpSession session) throws ControllerException {
		log.trace("?????? ??????");
		
	    UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	     
		 try {
		      String dbPw = this.mypageService.getUserDbPw(vo.getUser_email());
		      log.info("\t+ user_pw: {}", user_pw);
		      int result;
		          
		  if(encoder.matches(user_pw, dbPw)) { 
		      log.info("???????????? ??????");
		      result = 1;
		  
		      this.userService.userStatus(vo.getUser_email());
		  } else { log.info("???????????? ?????????"); result = 0; }// if-else

		      return result;
		  } catch (ServiceException e) { throw new ControllerException(e); }
		
	} // ?????? ?????? (????????? ??????)
	
	@GetMapping("/unregister/completed")	// GET
	public String unregisterCompleted( ) throws ControllerException {
		log.trace("??????????????? ?????? ??????");

		return "mypage/7-09_UnregisterCompleted";

	}// unregisterCompleted
		


//=====????????? ??????===============================================
	@GetMapping("/studentHands/use")
	public String studentHandsUse(CriteriaMyPage cri, @RequestParam String group,
			Model model, HttpSession session) throws ControllerException {
		log.trace("??????????????? ????????? ?????? ?????? ??????(??????)");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			if(group.equals("1")) {
				model.addAttribute("GROUP", group);
				
				List<UseHandQVO> list = this.mypageHandService.getAllMyUsehandtQList(cri);
				model.addAttribute("_MYUSEHAND_", list);
				
				PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageHandService.getMyUsehandQTotalAmount(cri));
				model.addAttribute("_MYUSEHANDPAGENATION_", pageDto);
			} else if (group.equals("2")){
				model.addAttribute("GROUP", group);
				
				List<UseHandTVO> list = this.mypageHandService.getAllMyUsehandtTList(cri);
				model.addAttribute("_MYUSEHAND_", list);
				
				PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageHandService.getMyUsehandTTotalAmount(cri));
				model.addAttribute("_MYUSEHANDPAGENATION_", pageDto);
			} 
			
			return "mypage/7-10_StudentHandsListUse";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
	}// studentHandsUse
	
	@GetMapping("/studentHands/buy")		//GET
	public String studentHandsBuy(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("??????????????? ????????? ?????? ?????? ??????(??????)");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<BuyVO> list = this.mypageHandService.myPageBuy(cri);
			model.addAttribute("_MYBUYHAND_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageHandService.myPageBuyCount(cri));
			model.addAttribute("_MYBUYHANDPAGENATION_", pageDto);
			
			return "mypage/7-11_StudentHandsListBuy";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// studentHandsBuy
	
	@GetMapping("/studentHands/buy/detail")		//GET
	public String studentHandsBuyDetail(@RequestParam Integer b_number, CriteriaMyPage cri, Model model) throws ControllerException {
		log.trace("??????????????? ????????? ?????? ?????? ??????(??????)");
		
		try {
			BuyInfoVO vo = this.mypageHandService.myPageBuyinfo(b_number);
			model.addAttribute("_BUYINFO_", vo);
			model.addAttribute("_CURRENTPAGE_", cri);
			
			return "mypage/7-12_StudentHandsListBuyD";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// studentHandsBuyDetail
	
	@GetMapping("/tutorHands/get")		//GET
	public String tutorHandsGet(CriteriaMyPage cri, @RequestParam String group,
			Model model, HttpSession session) throws ControllerException {
		log.trace("??????????????? ????????? ????????????(??????)");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			//??????????????? ??????
			int tpNum = this.mypageHandService.getTutorPageNum(vo.getUser_email());
			model.addAttribute("TpNum", tpNum);
			
			if(group.equals("1")) {
				model.addAttribute("GROUP", group);
				
				List<AnswerVO2> list = this.mypageHandService.getAllmyGetHandQList(cri);
				model.addAttribute("_MYGETHAND_", list);
				
				PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageHandService.getMyGetHandQTotalAmount(cri));
				model.addAttribute("_MYGETHANDPAGENATION_", pageDto);
			} else if (group.equals("2")){
				model.addAttribute("GROUP", group);
				
//				List<AnswerVO> list = this.mypageHandService.getAllmyGetHandQList(cri);
//				model.addAttribute("_MYGETHAND_", list);
				
				PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageHandService.getMyGetHandQTotalAmount(cri));
				model.addAttribute("_MYGETHANDPAGENATION_", pageDto);
			} 
			
			return "mypage/7-13_TutorHandsListGet";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
	}// tutorHandsGet
	
	
	@GetMapping("/tutorHands/withdraw")		//GET
	public String tutorHandsWithdraw(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("??????????????? ????????? ????????????(??????)");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<WithdrawalVO> list = this.mypageHandService.getAllMyWithdrawalList(cri);
			model.addAttribute("_MYWITHDRAWAL_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageHandService.getMyWithdrawalTotalAmount(cri));
			model.addAttribute("_MYWITHDRAWALPAGENATION_", pageDto);
			
			return "mypage/7-14_TutorHandsListWithdraw";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// tutorHandsWithdraw
	
//=====?????? ?????? ??????===============================================	
	// ???????????? ????????? 
	@GetMapping("/withdraw")
	public String withdraw(HttpSession session) throws ControllerException {
		log.trace("?????? ?????? ?????? ?????????");
		
		try {
			UserVO userVO = (UserVO) session.getAttribute("__LOGIN_USER__");
			String user_email = userVO.getUser_email();
			int hands_wallet = userVO.getHands_wallet();
			log.info("user_email, hands_wallet: {},{}", user_email,hands_wallet);
			
			return "mypage/7-15_Withdraw";
		} catch(Exception e) { throw new ControllerException(e); } // try-catch
		
	} // ?????? ?????? ?????? ?????????
	
	// ???????????? ?????? (?????? ?????? ?????? ?????????)
	@PostMapping("/withdrawAppilcation")
	public String withdrawAppilcation(WithdrawalDTO dto, HttpSession session) throws ControllerException {
		log.trace("?????? ?????? ??????");
		
		try { 
			this.mypageService.createWithdrawal(dto);
	
			UserVO vo = this.userService.getUserInfo(dto.getUser_email());
			session.setAttribute(SharedScopeKeys.LOGIN_USER, vo);
			
			return "mypage/7-16_WithdrawCompleted";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
	
	} // ?????? ?????? ?????? 
	
	
	@PostMapping("/withdraw/detail") 
	public String withdrawalDetail(CriteriaMyPage cri, Model model, WithdrawalDTO dto ) throws ControllerException {
		
		return "redirect:/mypage/tutorHands/withdraw?currPage="+ cri.getCurrPage();
	} // ?????? ?????? ???????????? ?????? (?????????????????? ?????? ?????? ???????????? ??????)

}//end class