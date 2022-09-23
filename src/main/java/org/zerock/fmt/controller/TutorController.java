package org.zerock.fmt.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.fmt.domain.AnswerDTO;
import org.zerock.fmt.domain.AnswerVO;
import org.zerock.fmt.domain.CriteriaReview;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.QuestionBoardDTO;
import org.zerock.fmt.domain.QuestionBoardVO;
import org.zerock.fmt.domain.ReviewDTO;
import org.zerock.fmt.domain.ReviewPageDTO;
import org.zerock.fmt.domain.ReviewVO;
import org.zerock.fmt.domain.TutorPageVO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.AnswerService;
import org.zerock.fmt.service.AskService;
import org.zerock.fmt.service.CommentService;
import org.zerock.fmt.service.ProfileService;
import org.zerock.fmt.service.ReviewService;
import org.zerock.fmt.service.TutorService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/tutor")
@Controller
public class TutorController {
	
	@Setter(onMethod_= @Autowired)
	private TutorService tutorService;
	
	@Setter(onMethod_= @Autowired)
	private AskService askService;
	
	@Setter(onMethod_= @Autowired)
	private AnswerService answerService;
	
	@Setter(onMethod_= @Autowired)
	private CommentService commentService;
	
	@Setter(onMethod_ =@Autowired)
	private ReviewService reviewService;
	
	@Setter(onMethod_= @Autowired)
	private ProfileService profileService;
	
	@GetMapping("/main")
	public String tpMain(Model model, HttpServletRequest req) throws ControllerException {
		log.trace("2-01_tpMain <<< 튜터페이지 메인");
		
		try {
			// 신규 튜터
			List<TutorPageVO> recentList = this.tutorService.getRecentTCard();
			recentList.forEach(log::trace);
			model.addAttribute("_RECENT_LIST_", recentList);
			
			// 추천 튜터
			String subject = "국어";
			String searchType = "누적답변순";
			
			if(req.getParameter("subject") != null) { subject = req.getParameter("subject"); }
			if(req.getParameter("searchType") != null) { searchType = req.getParameter("searchType"); }
			
			List<TutorPageVO> tutorVO = this.tutorService.getSortedTCard(subject, searchType);
			tutorVO.forEach(log::trace);
			model.addAttribute("_SORTED_TUTOR_", tutorVO);
			
		} catch (Exception e) { throw new ControllerException(e); }

		return "tutor/2-01_tpMain";
	} // tpMain
	

	@GetMapping("/info")
	public String tutorInfo(Model model,  HttpServletRequest req) throws ControllerException {
		log.trace("2-02_tutorpage_info <<< 튜터정보 페이지");
		
		String tp_number = req.getParameter("num");
		
		try {
			TutorPageVO tutorInfo = this.tutorService.getAllTInfo(tp_number);
			log.info("tutorInfo: {}", tutorInfo);
			model.addAttribute("_TUTOR_INFO_", tutorInfo);
			
			//--------------------------프로필 사진 가져오기
			String tutorEmail = this.profileService.getTutorEmail(tutorInfo.getTp_number());
			String tutorNick = this.profileService.getTutorNick(tutorInfo.getTp_number());
			model.addAttribute("_TUTORNICK_", tutorNick);
			
			List<ProfileVO> profileInfo = this.profileService.getProfile(tutorEmail);
			if(profileInfo.size() == 0) { model.addAttribute("_PROFILE_", "false"); }
			else { model.addAttribute("_PROFILE_", "true"); }
			//-----------------------------------------------
			
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "tutor/2-02_tutorpage_info";
	}// tutorInfo
	
	
	@GetMapping("/writeReview")
	public String writeReview(CriteriaReview cri,  Model model,  HttpServletRequest req) throws ControllerException {
		log.trace("2-03_writereview <<< 학생리뷰 작성 페이지");
		
		String tp_number = req.getParameter("num");
		
		try {
			TutorPageVO tutorInfo = this.tutorService.getAllTInfo(tp_number);
			log.info("tutorInfo: {}", tutorInfo);
			model.addAttribute("_TUTOR_INFO_", tutorInfo);
			
			//-----------------------------------리뷰
			cri.setTp_number(tutorInfo.getTp_number());
			List<ReviewVO> list = this.reviewService.getReview(cri);
			model.addAttribute("_RIVIEWLIST_",list);
			
			double avgReview = this.reviewService.avgReview(tutorInfo.getTp_number());
			model.addAttribute("avgReview",avgReview);
			Map<String,Object> avgStar = this.reviewService.countReview(tutorInfo.getTp_number());
			model.addAttribute("avgStar", avgStar);
			//-----------------------------------리뷰 프로필 

//			this.profileService.getProfile(tp_number)
//			
			
			//-----------------------------------리뷰페이징
			int totalReview = this.reviewService.countList(cri.getTp_number());
			ReviewPageDTO rvPage = new ReviewPageDTO(cri, totalReview);
			model.addAttribute("_REVIEWPAGINATION_",rvPage);
			model.addAttribute("RVCOUNT", totalReview);
			
			//--------------------------프로필 사진 가져오기
			String tutorEmail = this.profileService.getTutorEmail(tutorInfo.getTp_number());
			String tutorNick = this.profileService.getTutorNick(tutorInfo.getTp_number());
			model.addAttribute("_TUTORNICK_", tutorNick);
			
			List<ProfileVO> profileInfo = this.profileService.getProfile(tutorEmail);
			if(profileInfo.size() == 0) { model.addAttribute("_PROFILE_", "false"); }
			else { model.addAttribute("_PROFILE_", "true"); }
			//-----------------------------------------------
						
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "tutor/2-03_writereview";
	} // writeReview
	
	//리뷰등록
	@PostMapping("/createReview")
	public String writeReviewStudent(ReviewDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.trace("writeReviewStudent invoked.");
		try {
			int result = this.reviewService.createReview(dto);
			if(result==1) {
				rttrs.addFlashAttribute("_RESULT_REVIEW_","리뷰가 등록되었습니다.");
			}//if : 성공시 return result 
			return "redirect:/tutor/writeReview?num="+dto.getTp_number();
		}catch(Exception e) {throw new ControllerException(e); }
	}//writeReviewStudent
	
	//리뷰조회
	@GetMapping("/getReview")
	@ResponseBody
	public ReviewVO getReview(Integer rv_number) throws ControllerException {
		log.trace("modityReview invoked.");
			try{
				return this.reviewService.getRevirwDetail(rv_number);
			}catch(Exception e) { throw new ControllerException(e); }
	}//getReview
	
	//리뷰수정
	@PostMapping(value="/modifyReview",produces = "application/text; charset=utf8")
	@ResponseBody
	public String modify(@RequestBody ReviewDTO dto) throws ControllerException {
		log.trace("modityReview invoked.");
		log.info("dto: {}",dto);
			try{
				int result =this.reviewService.modifyReview(dto);
				if(result==1) {
					return "리뷰가 수정되었습니다.";
				}//if : 성공시 return result 
				return "fail";
			}catch(Exception e) { throw new ControllerException(e); }
	}//modityReview
	
	//리뷰삭제
	@GetMapping(value="/removeReview", produces = "application/text; charset=utf8")
	@ResponseBody
	public String removeReview(Integer tp_number,Integer rv_number) throws ControllerException {
		log.trace("modityReview invoked.");
			try{
				int result = this.reviewService.removeReview(rv_number);
				if(result==1) {
					return "리뷰가 삭제되었습니다.";
				}//if : 성공시 return result 
				return "fail";
			}catch(Exception e) { throw new ControllerException(e); }
	}//modityReview
	
	
	@GetMapping("/reviewList")
	public String reviewList(Model model,  HttpServletRequest req) throws ControllerException {
		log.trace("2-04_reviewlist <<< 학생리뷰 목록 페이지");
		
		String tp_number = req.getParameter("num");
		
		try {
			TutorPageVO tutorInfo = this.tutorService.getAllTInfo(tp_number);
			log.info("tutorInfo: {}", tutorInfo);
			model.addAttribute("_TUTOR_INFO_", tutorInfo);
			
			//--------------------------프로필 사진 가져오기
			String tutorEmail = this.profileService.getTutorEmail(tutorInfo.getTp_number());
			String tutorNick = this.profileService.getTutorNick(tutorInfo.getTp_number());
			model.addAttribute("_TUTORNICK_", tutorNick);
			
			List<ProfileVO> profileInfo = this.profileService.getProfile(tutorEmail);
			if(profileInfo.size() == 0) { model.addAttribute("_PROFILE_", "false"); }
			else { model.addAttribute("_PROFILE_", "true"); }
			//-----------------------------------------------
			
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "tutor/2-04_reviewlist";
	} // reviewList
	
	
	@GetMapping("/ask")
	public String ask(Model model,  HttpServletRequest req) throws ControllerException {
		log.trace("2-05_ask <<< 튜터에게 질문하기");
		
		String tp_number = req.getParameter("num");
		
		try {
			TutorPageVO tutorInfo = this.tutorService.getAllTInfo(tp_number);
			log.info("tutorInfo: {}", tutorInfo);
			model.addAttribute("_TUTOR_INFO_", tutorInfo);
			
			List<QuestionBoardVO> QBvo = this.askService.getQB(tp_number);
			log.info("QBvo :{}", QBvo);
			model.addAttribute("_QB_VO_",QBvo);
			
			//--------------------------프로필 사진 가져오기
			String tutorEmail = this.profileService.getTutorEmail(tutorInfo.getTp_number());
			String tutorNick = this.profileService.getTutorNick(tutorInfo.getTp_number());
			model.addAttribute("_TUTORNICK_", tutorNick);
			
			List<ProfileVO> profileInfo = this.profileService.getProfile(tutorEmail);
			if(profileInfo.size() == 0) { model.addAttribute("_PROFILE_", "false"); }
			else { model.addAttribute("_PROFILE_", "true"); }
			//-----------------------------------------------
			
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "tutor/2-05_ask";
	} // ask
	
	
	@GetMapping("/writeAnswer")
	public String writeAnswer(Model model, HttpServletRequest req) throws ControllerException {
		log.trace("2-06_writeAnswer <<< 답변 작성하기 - 튜터");

		String qb_number = req.getParameter("num");
		String tp_number = req.getParameter("tp");
		
		try {
			QuestionBoardVO oneQ = this.askService.getOneQ(qb_number);
			log.info("oneQ: {}", oneQ);
			model.addAttribute("_ONE_Q_", oneQ);
			
			TutorPageVO Tvo = this.tutorService.getAllTInfo(tp_number);
			String Tname = Tvo.getUser_name();
			log.info("Tname: {}", Tname);
			model.addAttribute("_T_NAME_", Tname);
			
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "tutor/2-06_writeAnswer";
	} // writeAnswer
	
	
	@PostMapping("/createAnswer")
	public String createAnswer(@ModelAttribute("updateDTO") AnswerDTO newA, HttpServletRequest req) throws ControllerException {
		log.trace("<<< 답변 등록");
		
		String qb_number = req.getParameter("num");
		try {
			boolean result = this.answerService.createA(newA);
			log.info("result: {}", result);
			
			HttpSession session = req.getSession();
			UserVO userVO = (UserVO) session.getAttribute("__LOGIN_USER__");
			
			// session 손들기 정보 업데이트
			int hands = (int) userVO.getHands_wallet();
			log.info("============= session hands: {}", hands);
			
			userVO.setHands_wallet(hands + 3);
			session.setAttribute("__LOGIN_USER__", userVO);
			log.info("============= after hands: {}", userVO.getHands_wallet());
			
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "redirect:/tutor/watchAnswer?num=" + qb_number;
	} // createAnswer
	
	
	@GetMapping("/watchAnswer")
	public String watchAnswer(Model model, HttpServletRequest req) throws ControllerException {
		log.trace("2-07_watchAnswer <<< 답변 보기 - 학생, 튜터");
		
		String qb_number = req.getParameter("num");
		String tp_number = req.getParameter("tp");
		
		try {
			// 해당 튜터페이지의 튜터 이메일 정보
			QuestionBoardVO oneQ = this.askService.getOneQ(qb_number);
			Integer tpNum = oneQ.getTp_number();
			String TEmail = this.tutorService.getTEmail(tpNum);
			
			// 현재 로그인한 유저의 이메일 정보
			HttpSession session = req.getSession();
			UserVO userVO = (UserVO) session.getAttribute("__LOGIN_USER__");
			String userEmail = userVO.getUser_email();
			log.info("userEmail : ======= {}", userEmail);
			
			// 답변 정보
			AnswerVO Avo = this.answerService.getA(qb_number);
			
			// 두 이메일 주소가 일치하고 답변이 없는 경우 
			if(TEmail.equals(userEmail) && Avo == null) {
				log.info("답변 작성 페이지로 이동");
				return "redirect:/tutor/writeAnswer?num=" + qb_number + "&tp=" + tp_number;
			}
			
			model.addAttribute("_ONE_Q_", oneQ);
			model.addAttribute("_A_", Avo);
			
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "tutor/2-07_watchAnswer";
	} // watchAnswer
	
	
	@PostMapping("/modifyAsk")
	public String modifyAsk(@ModelAttribute("updateDTO") QuestionBoardDTO updateDTO, HttpServletRequest req) throws ControllerException {
		log.trace("<<< 질문글 수정");
		
		String qb_number = req.getParameter("num");
		
		try {
			boolean result = this.askService.updateQ(updateDTO);
			log.info("result: {}", result);
			
		} catch (ServiceException e) { throw new ControllerException(e); }
		
		return "redirect:/tutor/watchAnswer?num=" + qb_number;
	} // modifyAsk
	
	// 파일 업로드 처리
	
	
	@PostMapping("/createAsk")
	public String createAsk(@ModelAttribute("updateDTO") QuestionBoardDTO newDTO, HttpServletResponse res, HttpServletRequest req, List<MultipartFile> files) throws ControllerException {
		log.trace("<<< 질문글 등록");
		
		// 튜터페이지의 튜터에게 질문글 등록
		String tp_number = req.getParameter("num");
		
		HttpSession session = req.getSession();
		UserVO userVO = (UserVO) session.getAttribute("__LOGIN_USER__");
		String user_email = userVO.getUser_email();
		log.info("user_email: {}", user_email);
		
		newDTO.setUser_email(user_email);
		try {
			boolean result = this.askService.createQ(newDTO);
			log.info("result: {}", result);
			
			// session 손들기 정보 업데이트
			int hands = (int) userVO.getHands_wallet();
			log.info("============= session hands: {}", hands);
			
			userVO.setHands_wallet(hands - 3);
			session.setAttribute("__LOGIN_USER__", userVO);
			log.info("============= after hands: {}", userVO.getHands_wallet());
			
		} catch (ServiceException e) { throw new ControllerException(e); }
		
		return "redirect:/tutor/ask?num=" + tp_number;
	} // createAsk
	
	
	@GetMapping("/deleteAsk")
	public String deleteAsk(HttpServletRequest req) throws ControllerException {
		log.trace("<<< 질문글 삭제");
		
		String qb_number = req.getParameter("num");
		String tp_number = req.getParameter("tp");
		
		HttpSession session = req.getSession();
		UserVO userVO = (UserVO) session.getAttribute("__LOGIN_USER__");
		String user_email = userVO.getUser_email();
		log.info("user_email: {}", user_email);
		
		try {
			boolean result = this.askService.deleteQ(qb_number, user_email);
			log.info("result: {}", result);
			
			// session 손들기 정보 업데이트
			int hands = (int) userVO.getHands_wallet();
			log.info("============= session hands: {}", hands);
			
			userVO.setHands_wallet(hands + 3);
			session.setAttribute("__LOGIN_USER__", userVO);
			log.info("============= after hands: {}", userVO.getHands_wallet());
			
		} catch (ServiceException e) { throw new ControllerException(e); }
		
		return "redirect:/tutor/ask?num=" + tp_number; 
	} // modifyAsk
	
	
	@PostMapping("/modifyAnswer")
	public String modifyAnswer(@ModelAttribute("updateDTO") AnswerDTO updateDTO, HttpServletRequest req) throws ControllerException {
		log.trace("<<< 답변글 수정");
		
		String qb_number = req.getParameter("num");
		
		try {
			boolean result = this.answerService.updateA(updateDTO);
			log.info("result: {}", result);
			
		} catch (ServiceException e) { throw new ControllerException(e); }
		
		return "redirect:/tutor/watchAnswer?num=" + qb_number;
	} // modifyAnswer
	
	
	@GetMapping("/tutoring")
	public String tutoring(Model model, HttpServletRequest req) throws ControllerException {
		log.trace("2-08_tutoring <<< 튜터에게 과외받기");
		
		String tp_number = req.getParameter("num");
		
		try {
			TutorPageVO tutorInfo = this.tutorService.getAllTInfo(tp_number);
			log.info("tutorInfo: {}", tutorInfo);
			model.addAttribute("_TUTOR_INFO_", tutorInfo);
			
			//--------------------------프로필 사진 가져오기
			String tutorEmail = this.profileService.getTutorEmail(tutorInfo.getTp_number());
			String tutorNick = this.profileService.getTutorNick(tutorInfo.getTp_number());
			model.addAttribute("_TUTORNICK_", tutorNick);
			
			List<ProfileVO> profileInfo = this.profileService.getProfile(tutorEmail);
			if(profileInfo.size() == 0) { model.addAttribute("_PROFILE_", "false"); }
			else { model.addAttribute("_PROFILE_", "true"); }
			//-----------------------------------------------
			
		} catch (Exception e) { throw new ControllerException(e); }
		
		return "tutor/2-08_tutoring";
	} // tutoring
	
	
	@GetMapping("/tutoringAsk")
	public String tutoringAsk() {
		log.trace("2-09_tutoringAsk <<< 과외하기 질문");
		
		return "tutor/2-09_tutoringAsk";
	} // tutoringAsk


} // end class
