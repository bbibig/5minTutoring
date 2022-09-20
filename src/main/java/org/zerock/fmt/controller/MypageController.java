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
import org.zerock.fmt.domain.CommentVO2;
import org.zerock.fmt.domain.CommunityVO2;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.InquiryAnswerVO;
import org.zerock.fmt.domain.InquiryQuestionVO;
import org.zerock.fmt.domain.PageMyPageDTO;
import org.zerock.fmt.domain.ProfileDTO;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.UseHandVO2;
import org.zerock.fmt.domain.UserDTO;
import org.zerock.fmt.domain.UserVO;
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

@RequestMapping("/mypage")	//기본URI(Base URI)
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
	
//=====기본정보===============================================	
	@GetMapping("/studentPage")
	public String studentPage(Model model, HttpSession session) throws ControllerException{
		log.trace("마이페이지 기본정보 조회(학생)");
		
		try {
			//1. 기본정보 조회
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			log.info("1. session scope 정보: {}", vo);
			
			UserVO userInfo = this.userService.getUserInfo(vo.getUser_email());
			model.addAttribute("_USERINFO_", userInfo);
			
			return "mypage/7-01_StudentPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 기본정보조회(학생)
	
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
				log.info("비밀번호 일치");
				result = 1;
			} else { log.info("비밀번호 불일치"); result = 0; }// if-else

			return result;
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
	}// 비밀번호 유효성 체크(ajax로 처리)
	
	@PostMapping("/studentPageModify")
	public String studentPage(UserDTO dto, @RequestParam String user_newPw, MultipartFile file_name,
			RedirectAttributes rttrs, HttpSession session) throws ControllerException{
		log.trace("마이페이지 기본정보 수정(학생)");
		log.info("\t+ 1. file_name: {}", file_name);
		
		try {
			log.info("\t+ user_newPw: {}", user_newPw);
			dto.setUser_pw(user_newPw);
			
			if(file_name.getSize() != 0) {	//input file에 파일 값이 있다면
				//1-1. 프로필 사진 유무 조회
				List<ProfileVO> profileVo = this.profileService.getProfile(dto.getUser_email());
				
				ProfileDTO profileDto = new ProfileDTO();
				profileDto.setUser_email(dto.getUser_email());
				profileDto.setFile_name(dto.getUser_nick() + "_profile");
				
				//1-2. DB에 정보 저장 또는 수정
				if(profileVo.size() == 0) { //DB에 프로필 정보가 없다면, 
					this.profileService.createProfile(profileDto);
					session.setAttribute(SharedScopeKeys.USER_PROFILE, "true");
					log.info("프로필 정보 DB 생성");
				} else { 
					this.profileService.modifyProfile(profileDto);
					log.info("프로필 정보 DB 수정");
				}// if- else
				
				//1-3. 로컬에 저장
				profileUpload.uploadProfile(file_name, dto);
				log.info("프로필 사진 로컬 저장소 업로드 완료");
			}//if
			
			//2. 회원정보 수정
			if(this.mypageService.modifyUserInfo(dto)) {
				UserVO vo = this.userService.getUserInfo(dto.getUser_email());
				session.setAttribute(SharedScopeKeys.LOGIN_USER, vo);
				
				rttrs.addFlashAttribute("_USERMODIFYRESULT_", "회원정보 수정 성공");
			}// if
			return "redirect:/mypage/studentPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 기본정보 수정(학생)
	
	
	@GetMapping("/tutorPage")
	public String tutorPage(Model model, HttpSession session) throws ControllerException{
		log.trace("마이페이지 기본정보 조회(튜터)");
		
		try {
			//1. 기본정보 조회
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			log.info("1. session scope 정보: {}", vo);
			
			UserVO userInfo = this.userService.getUserInfo(vo.getUser_email());
			model.addAttribute("_USERINFO_", userInfo);
			
			return "mypage/7-02_TutorPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
	}// 기본정보 조회(튜터)
	
	@PostMapping("/tutorPageModify")
	public String tutorPageModify(UserDTO dto, @RequestParam String user_newPw, MultipartFile file_name,
			RedirectAttributes rttrs, HttpSession session) throws ControllerException{
		log.trace("마이페이지 기본정보 수정(튜터)");
		log.info("\t+ 1. file_name: {}", file_name);
		
		try {
			log.info("\t+ user_newPw: {}", user_newPw);
			dto.setUser_pw(user_newPw);
			
			if(file_name.getSize() != 0) {	//input file에 파일 값이 있다면
				//1-1. 프로필 사진 유무 조회
				List<ProfileVO> profileVo = this.profileService.getProfile(dto.getUser_email());
				
				ProfileDTO profileDto = new ProfileDTO();
				profileDto.setUser_email(dto.getUser_email());
				profileDto.setFile_name(dto.getUser_nick() + "_profile");
				
				//1-2. DB에 정보 저장 또는 수정
				if(profileVo.size() == 0) { //DB에 프로필 정보가 없다면, 
					this.profileService.createProfile(profileDto); 
					session.setAttribute(SharedScopeKeys.USER_PROFILE, "true");
					log.info("프로필 정보 DB 생성");
				} else { 
					this.profileService.modifyProfile(profileDto);
					log.info("프로필 정보 DB 수정");
				}// if- else
				
				//1-3. 로컬에 저장
				profileUpload.uploadProfile(file_name, dto);
				log.info("프로필 사진 로컬 저장소 업로드 완료");
			}//if
			
			//2. 회원정보 수정
			if(this.mypageService.modifyUserInfo(dto)) {
				UserVO vo = this.userService.getUserInfo(dto.getUser_email());
				session.setAttribute(SharedScopeKeys.LOGIN_USER, vo);
				
				rttrs.addFlashAttribute("_USERMODIFYRESULT_", "회원정보 수정 성공");
			}// if
			return "redirect:/mypage/tutorPage";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 기본정보 수정(튜터)
	
//=====나의 질문글===============================================
	@GetMapping("/myQuestion")
	public String myQuestion(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("마이페이지 나의 질문글 목록 조회");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<QuestionBoardVO> list = this.mypageService.getAllMyQuestionList(cri);
			model.addAttribute("_MYQLIST_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageService.getMyQuestionTotalAmount(vo.getUser_email()));
			model.addAttribute("_MYQLISTPAGENATION_", pageDto);
						
			return "mypage/7-03_MyQuestionList";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 나의 질문글 조회

//=====나의 커뮤니티===============================================
	@GetMapping("/community/write")		//GET	
	public String communityWrite(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("마이페이지 나의 커뮤니티 작성글 목록 조회");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<CommunityVO2> list = this.mypageService.getAllMyCommunityList(cri);
			model.addAttribute("_MYCOMMUNITY_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageService.getMyCommunityTotalAmount(vo.getUser_email()));
			model.addAttribute("_MYCOMMUNITYPAGENATION_", pageDto);
						
			return "mypage/7-04_CommunityW";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 나의 작성글
	
	@GetMapping("/community/comments")		//GET	
	public String communityComments(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("마이페이지 나의 댓글 목록 조회");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<CommentVO2> list = this.mypageService.getAllMyCommentList(cri);
			model.addAttribute("_MYCOMMENT_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageService.getMyCommentTotalAmount(vo.getUser_email()));
			model.addAttribute("_MYCOMMENTPAGENATION_", pageDto);
						
			return "mypage/7-05_CommunityC";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 나의 댓글 조회
	
	
//===== 나의 1:1 문의 ===============================================	
	@GetMapping("/qList")	// GET
	public String qList(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("마이페이지 나의 문의 목록 조회");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			List<InquiryQuestionVO> list = this.mypageService.getAllMyInquiryList(cri);
			model.addAttribute("_MYINQUIRY_", list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageService.getMyInquiryTotalAmount(vo.getUser_email()));
			model.addAttribute("_MYINQUIRYPAGENATION_", pageDto);
						
			return "mypage/7-06_QList";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch

	}// 나의 문의 목록 조회	
	
	
	// 필요없지만 혹시나 해서 나중에 지우겠습니다!
//	@GetMapping("/question") // GET
//	public String question(InquiryQuestionDTO dto, Model model, HttpSession session) {
//		log.trace("7-07_Q");
//		
//		return "mypage/7-07_Q";
//	}// question
	
	@GetMapping("/qAndA")	// GET
	public String qAndA(@RequestParam Integer iq_number,CriteriaMyPage cri, Model model) throws ControllerException {
		log.trace("마이페이지 일대일 문의와 답변 조회");
		log.info(iq_number); 
		
		try {
			InquiryQuestionVO Questionvo = this.iqService.getInquiry(iq_number);
			InquiryAnswerVO Answervo = this.iaService.getIA(iq_number);
			log.info("\t+ vo: " + Questionvo);
			log.info("\t+ vo: " + Answervo);
			
			// 문의
			model.addAttribute("_INQUIRYQUESTION_", Questionvo);	
			model.addAttribute("_CURRENTPAGE_", cri);
			
			// 답변
			model.addAttribute("_INQUIRYANSWER_",Answervo);
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
		return "mypage/7-07_QandA";
	} // 일대일 문의&답변 조회
	
	@RequestMapping("/unregister")	// POST
	public String unregister() {
		log.trace("7-08_Unregister");
		
		return "mypage/7-08_Unregister";
	}// unregister
	
	@GetMapping("/unregister/completed")	// GET
	public String unregisterCompleted() {
		log.trace("7-09_UnregisterCompleted");
		
		return "mypage/7-09_UnregisterCompleted";
	}// unregisterCompleted
	
	
//=====손들기 내역===============================================
	@GetMapping("/studentHands/use")
	public String studentHandsUse(CriteriaMyPage cri, @RequestParam String group,
			Model model, HttpSession session) throws ControllerException {
		log.trace("마이페이지 손들기 사용 목록 조회(학생)");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			if(group.equals("1")) {
				model.addAttribute("GROUP", group);
				
				List<UseHandVO2> list = this.mypageHandService.getAllMyUsehandtQList(cri);
				model.addAttribute("_MYUSEHAND_", list);
				
				PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageHandService.getMyUsehandQTotalAmount(cri));
				model.addAttribute("_MYUSEHANDPAGENATION_", pageDto);
			} else if (group.equals("2")){
				model.addAttribute("GROUP", group);
				
				List<UseHandVO2> list = this.mypageHandService.getAllMyUsehandtTList(cri);
				model.addAttribute("_MYUSEHAND_", list);
				
				PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.mypageHandService.getMyUsehandTTotalAmount(cri));
				model.addAttribute("_MYUSEHANDPAGENATION_", pageDto);
			} 
			
			return "mypage/7-10_StudentHandsListUse";
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
	}// studentHandsUse
	
	@GetMapping("/studentHands/buy")		//GET
	public String studentHandsBuy(CriteriaMyPage cri, Model model, HttpSession session) throws ControllerException {
		log.trace("마이페이지 손들기 구매 목록 조회(학생)");
		
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
		log.trace("마이페이지 손들기 구매 상세 조회(학생)");
		
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
		log.trace("마이페이지 손들기 획득내역(튜터)");
		
		try {
			UserVO vo = (UserVO) session.getAttribute(SharedScopeKeys.LOGIN_USER);
			cri.setUser_email(vo.getUser_email());
			
			//튜터페이지 번호
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
		log.trace("마이페이지 손들기 출금내역(튜터)");
		
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
	
	// 출금 신청 페이지
	@GetMapping("/withdraw")
	public String withdraw(Model model, HttpSession session) throws ControllerException {
		log.trace("withdraw() invoked.");
		
		try {
			UserVO userVO = (UserVO) session.getAttribute("__LOGIN_USER__");
			String user_email = userVO.getUser_email();
			int hands_wallet = userVO.getHands_wallet();
			log.info("user_email, hands_wallet: {},{}", user_email,hands_wallet);
			
		} catch(Exception e) {
			throw new ControllerException(e);
		} // try-catch
		
		return "mypage/7-15_Withdraw";
	} // 튜터 출금 신청 페이지
	
	// 출금 신청 (post)
	@PostMapping("/withdraw/application")	
	public String withdrawApplication() {
		log.trace("withdrawApplication() invoked.");
		
		
		return "mypage/7-15_Withdraw";
	} // 출금 신청 
	
	
	@GetMapping("/withdraw/completed")	// GET
	public String withdrawCompleted() {
		log.trace("7-16_WithdrawCompleted");
		
		return "mypage/7-16_WithdrawCompleted";
	}// withdrawCompleted

}//end class