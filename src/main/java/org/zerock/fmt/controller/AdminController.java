package org.zerock.fmt.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.fmt.domain.AdminPageDTO;
import org.zerock.fmt.domain.AdminVO;
import org.zerock.fmt.domain.BuyVO;
import org.zerock.fmt.domain.CriteriaAdmin;
import org.zerock.fmt.domain.CriteriaMyPage;
import org.zerock.fmt.domain.FaqDTO;
import org.zerock.fmt.domain.FaqVO;
import org.zerock.fmt.domain.FileVO;
import org.zerock.fmt.domain.PageMyPageDTO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.exception.ControllerException;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.AdminService;
import org.zerock.fmt.service.BuyService;
import org.zerock.fmt.service.FaqService;
import org.zerock.fmt.service.FileService;
import org.zerock.fmt.service.UserService;

import edu.emory.mathcs.backport.java.util.Arrays;
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
	
	@Setter(onMethod_ = @Autowired)
	private FileService fileService;
	
	@Setter(onMethod_ = @Autowired)
	private BuyService buyService;
	//--------------------------------------------- 어드민로그인
	@GetMapping
	public String adminLogin() {
		log.info("어드민 Home");
		return "admin/8-00_adminLogin";
	}
	@PostMapping("/login")
	public String adminLogin(String ad_id,String ad_pw, Model model,RedirectAttributes rttrs) throws ControllerException {
		log.info("어드민 로그인");
		try {
			
			AdminVO admin = adminService.Login(ad_id, ad_pw);
			if(admin == null) {
				rttrs.addFlashAttribute("_RESULT_","관리자 계정이 없습니다.");
				return "redirect:/admin";
			} else {
				model.addAttribute("_ADMIN_", admin);
				model.addAttribute("_ADMIN_RESULT_","님, 로그인 하였습니다.");
				return "admin/Loginpost";
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
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.userService.userCount(cri));
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
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.userService.userCount(cri));
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
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.userService.userCount(cri));
			model.addAttribute("_ADMINPAGINATION_",Adpage);
		}catch(Exception e) {throw new ControllerException(e); }//try-catch
		return "admin/8-01_managerTZ";
	}
	
	@RequestMapping("/UserInfo")
	public String UserInfo(String user_email, Model model) throws ControllerException {
		log.info("회원정보조회");
		try {
			UserVO userinfo = this.userService.getUserInfo(user_email);
			model.addAttribute("_USERINFO_",userinfo);
			return "admin/8-01_userInfo";
		} catch( Exception e) {throw new ControllerException(e); }//try-catch
		
	}//UserInfo
	
	@RequestMapping("/tutorInfo")
	public String tutorInfo(String user_email, Model model) throws ControllerException {
		log.info("튜터승인정보조회");
		
		try {
			UserVO userInfo = this.userService.getUserInfo(user_email);
			model.addAttribute("_USERINFO_", userInfo);
			FileVO ttFile = this.fileService.getFile(user_email);
			model.addAttribute("_TUTOR_FILE_",ttFile);
			return "admin/8-01_tutorInfo";
		} catch(Exception e) {throw new ControllerException(e); }//try-catch
	}//tutorInfo
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
	public String adminSale(CriteriaAdmin cri, Model model) throws ControllerException {
		log.info("매출관리 페이지");
		try {
			List<BuyVO> buyList = this.buyService.selectAllBuy(cri);
			model.addAttribute("_BUYLIST_",buyList);
			AdminPageDTO Adpage = new AdminPageDTO(cri, this.buyService.countBuy());
			model.addAttribute("_ADMINPAGINATION_",Adpage);
			int countSale = this.buyService.countSale();
			model.addAttribute("countSale",countSale);
			return "admin/8-05_sale_sell";			
		} catch (Exception e) {throw new ControllerException(e); }//try-catch
	}//adminSale
	
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

	@PostMapping("/signUpOK")
	public String signUp_comfim(@RequestParam HashMap<String, Object> commandMap, RedirectAttributes rttrs) 
								throws ControllerException {
		log.trace("튜터가입승인 페이지");

		try {
			String getEmails = commandMap.get("arrayParam").toString();
			String[] emails = null;
			emails = getEmails.split(",");
			
			int result = 0;
			for(String user_email : emails) {
				result += this.userService.tutorPass(user_email);
			}//for
			if(result>0) {
				rttrs.addFlashAttribute("TutorResult", "승인처리되었습니다.");
			}
			log.info("\t + 튜터승인 List : {}, result : {}", Arrays.toString(emails), result);
			
			return "redirect:/admin/signUp_comfim";
		}catch(Exception e) { throw new ControllerException(e); }//try-catch
	}//signUp_comfim
	
	@GetMapping("/display")
	public ResponseEntity<byte[]> getImage (String fileName) {
		log.trace("파일띄우기");
		File file = new File(fileName); //*** 경로 겹치지 않게 설정해야함 ***
		
		ResponseEntity<byte[]> result = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-type", Files.probeContentType(file.toPath()));
			result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return result;
	}//uploadFile
	
	
}//end class
