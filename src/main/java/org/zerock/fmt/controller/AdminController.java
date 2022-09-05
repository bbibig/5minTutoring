package org.zerock.fmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.fmt.domain.AdminPageDTO;
import org.zerock.fmt.domain.AdminVO;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.FaqDTO;
import org.zerock.fmt.domain.FaqVO;
import org.zerock.fmt.domain.PageMyPageDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.AdminService;
import org.zerock.fmt.service.FaqService;
import org.zerock.fmt.service.UserService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/admin")
@Controller
public class AdminController {
	
	@Setter(onMethod_= @Autowired)
	private FaqService faqService;

	@Setter(onMethod_ =@Autowired)
	private AdminService adminService;
		
	@Setter(onMethod_ = @Autowired)
	private UserService userService;
	//--------------------------------------------- 어드민로그인
	@GetMapping
	public String adminLogin() {
		log.info("어드민 Home");
		return "admin/8-00_adminLogin";
	}
	@PostMapping
	public String adminLogin(String ad_id,String ad_pw, Model model,RedirectAttributes rttrs) throws ControllerException {
		log.info("어드민 로그인");
		try {
			
			AdminVO admin = adminService.Login(ad_id, ad_pw);
			rttrs.addFlashAttribute("_ADMIN_", admin);
			if(admin == null) {
				rttrs.addFlashAttribute("_ADMIN_RESULT_","관리자 계정이 없습니다.");
				return "redirect:/admin";
			} else {
				rttrs.addFlashAttribute("_ADMIN_RESULT_","님, 로그인 하였습니다.");
				return "redirect:/admin/student";
			}
		} catch (Exception e) { throw new ControllerException(e); }//try-catch
	}//adminLogin
	
	//--------------------------------------------- 회원조회
	@GetMapping("/student")
	public String adminMemberStrudent(Model model, CriteriaAdmin cri) throws ControllerException {
		log.info("회원리스트 - 학생 페이지");
		try {
			List<UserVO> list = this.userService.getStudent(cri);
			model.addAttribute("_USERLIST_",list);
			log.info("\t + 1. 여기까지");
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.userService.userCount("Student", null));
			log.info("\t+ Adpage : {}", Adpage);
			model.addAttribute("_ADMINPAGINATION_",Adpage);
		} catch (Exception e) {throw new ControllerException(e); }//try-catch
		return "admin/8-01_managerST";
	}
	
	@GetMapping("/tutor")
	public String adminMemberTutor(Model model,CriteriaAdmin cri) throws ControllerException {
		log.info("회원리스트 - 튜터 페이지 ");
		try {
			List<UserVO> list = this.userService.getTutor(cri);
			model.addAttribute("_USERLIST_",list);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.userService.userCount("Tutor", null));
			model.addAttribute("_ADMINPAGINATION_",Adpage);
		}catch(Exception e) {throw new ControllerException(e); }//try-catch
		return "admin/8-01_managerTT";
	}
	
	@GetMapping("/humenMember")
	public String adminMemberTZ(Model model,CriteriaAdmin cri) throws ControllerException {
		log.info("회원리스트 - 탈퇴회원");
		try {
			List<UserVO> list = this.userService.getStopUser(cri);
			model.addAttribute("_USERLIST_",list);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.userService.userCount(null,"STOP"));
			model.addAttribute("_ADMINPAGINATION_",Adpage);
		}catch(Exception e) {throw new ControllerException(e); }//try-catch
		return "admin/8-01_managerTZ";
	}
	
	@RequestMapping("/UserInfo")
	public String UserInfo() {
		log.info("회원정보조회");
		
		return "admin/8-01_userInfo";
	}
	
	@RequestMapping("/tutorInfo")
	public String tutorInfo(Model model) {
		log.info("회원정보조회");
		
//		TutorVO tutor = 
//				new TutorVO("1234@email", "튜터닉네임", "000000", "11월11일", "대핟생", "수학", "이거는첨부파일");
		
//		model.addAttribute("tutor",tutor);
		
		
		return "admin/8-01_tutorInfo";
	}
	//--------------------------------------------- 관리자조회
	@GetMapping("/stator")
	public String adminStator(CriteriaAdmin cri, Model model) throws ControllerException {
		log.info("관리자리스트");
		
		try {
			List<AdminVO> list = this.adminService.adminList(cri);
			model.addAttribute("_USERLIST_",list);
			list.forEach(log::info);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.adminService.adminCount());
			model.addAttribute("_ADMINPAGINATION_",Adpage);
		}catch(Exception e) {throw new ControllerException(e); }//try-catch
		return "admin/8-02_administrator";
	}
	
	//--------------------------------------------- 문의게시판
	@RequestMapping("/answerBoard_OK")
	public String adminAnswerBoardOk() {
		log.info("문의게시판(답변완료)");
		
		return "admin/8-03_answerBoard_OK";
	}
	
	@RequestMapping("/answerBoard_NO")
	public String adminAnswerBoardNo() {
		log.info("문의게시판(미답변)");
		
		return "admin/8-03_answerBoard_NO";
	}
	
	@RequestMapping("/answerBoard/comment")
	public String adminAnswerBoard_comment() {
		log.info("문의게시판(미답변)");
		
		return "admin/8-03_answerBoard_comment";
	}
	
//	관리자 FAQ ========================================================================================
	@GetMapping("/adminFAQ")
	public String adminFAQ(CriteriaMyPage cri, Model model) throws ControllerException {
		log.info("자주 묻는 질문(관리자용)");
		
		try {
			List<FaqVO> list = this.faqService.getFaqList(cri);
			model.addAttribute("_FAQLIST_",list);
			
			PageMyPageDTO pageDto = new PageMyPageDTO(cri, this.faqService.getFaqTotal());
			model.addAttribute("_FAQPAGENATION_",pageDto);
			
			return "admin/8-04_admin_faq";
		} catch (ServiceException e) {
			throw new ControllerException(e);
		}// try-catch
	}// adminFAQ(조회)
	
	@PostMapping("/adminFAQCreate")
	public String adminFAQCreate(CriteriaMyPage cri, FaqDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.info("자주 묻는 질문(관리자용) 생성");
		
		try {
			if(this.faqService.createFaq(dto)) { rttrs.addFlashAttribute("_FAQRESULT_", "FAQ생성 성공"); } 
			else { rttrs.addFlashAttribute("_FAQRESULT_", "FAQ생성 오류"); } //if - else
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
		return "redirect:/admin/adminFAQ?currPage="+ cri.getCurrPage();
	}// adminFAQ(생성)
	
	@PostMapping("/adminFAQModify")
	public String adminFAQModify(CriteriaMyPage cri, FaqDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.info("자주 묻는 질문(관리자용) 수정");
		
		try {
			if(this.faqService.updateFaq(dto)) { rttrs.addFlashAttribute("_FAQRESULT_", "FAQ수정 성공"); } 
			else { rttrs.addFlashAttribute("_FAQRESULT_", "FAQ수정 오류"); } //if - else
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
		return "redirect:/admin/adminFAQ?currPage="+ cri.getCurrPage();
	}// adminFAQ(수정)
	
	@PostMapping("/adminFAQRemove")
	public String adminFAQRemove(CriteriaMyPage cri, FaqDTO dto, RedirectAttributes rttrs) throws ControllerException {
		log.info("자주 묻는 질문(관리자용) 삭제");
		
		try {
			if(this.faqService.removeFaq(dto)) { rttrs.addFlashAttribute("_FAQRESULT_", "FAQ삭제 성공"); } 
			else { rttrs.addFlashAttribute("_FAQRESULT_", "FAQ삭제 오류"); } //if - else
		} catch (ServiceException e) { throw new ControllerException(e); }// try-catch
		
		return "redirect:/admin/adminFAQ?currPage="+ cri.getCurrPage();
	}// adminFAQ(삭제)
//	===================================================================================================

	@RequestMapping("/sale/sell")
	public String adminSale() {
		log.info("매출관리 페이지");
		//buy service
		return "admin/8-05_sale_sell";
	}
	
	@RequestMapping("/sale/withdrow")
	public String adminWithDrow() {
		log.info("출금 페이지");
		//Withdrawal sevice 
		return "admin/8-05_sale_withdrow";
	}
	
	//--------------------------------------------- 튜터가입승인
	@GetMapping("/signUp_comfim")
	public String signUp_comfim(CriteriaAdmin cri,Model model) throws ControllerException {
		log.trace("튜터가입승인 페이지 ");
		try{
			List<UserVO> list = this.userService.getWaitTutor(cri);
			model.addAttribute("_USERLIST_",list);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.userService.waitTutorCount());
			model.addAttribute("_ADMINPAGINATION_",Adpage);
		}catch(Exception e) {throw new ControllerException(e);}//try-catch
		return "admin/8-06_singUpConfim";
	}
	
	@PostMapping("/signUp_comfim")
	public String signUp_comfim(String user_email) {
		log.trace("튜터가입승인");
		log.info("\t+ user_eamil : {}", user_email);
		
		return "admin/8-06_singUpConfim";
	}//signUp_comfim
	
	
}//end class
