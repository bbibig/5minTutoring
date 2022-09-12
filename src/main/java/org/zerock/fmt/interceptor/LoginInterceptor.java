package org.zerock.fmt.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.fmt.common.SharedScopeKeys;
import org.zerock.fmt.domain.ProfileVO;
import org.zerock.fmt.domain.UserVO;
import org.zerock.fmt.service.ProfileService;
import org.zerock.fmt.service.UserService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	@Setter(onMethod_= @Autowired)
	private ProfileService profileService;
	

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		log.trace("loginInterceptor preHandle invoked.");
		
		HttpSession session = req.getSession();
		session.removeAttribute(SharedScopeKeys.LOGIN_KEY);
		session.removeAttribute(SharedScopeKeys.USER_PROFILE);
		log.info("\t + LoginInterceptor : Session remove.");
		
		return true;
	}//preHandle
	
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.trace("LoginInterceptor postHandler invoked.");
		
		ModelMap modelMap = modelAndView.getModelMap();
		UserVO loginVO = (UserVO) modelMap.getAttribute(SharedScopeKeys.LOGIN_USER);
		log.info("\t+ loginVO for Session : {}", loginVO);
		//로그인 된 유저 SessionScope에 바인딩 
		req.getSession().setAttribute(SharedScopeKeys.LOGIN_USER, loginVO);
//		res.sendRedirect("/login/home");
		
		//프로필 사진 유무 조회
		List<ProfileVO> profileVo = this.profileService.getProfile(loginVO.getUser_email());
		if(profileVo.size() == 0 ) {
			req.getSession().setAttribute(SharedScopeKeys.USER_PROFILE, "false");
		} else {
			req.getSession().setAttribute(SharedScopeKeys.USER_PROFILE, "true");
		}// if-else
		
	}//postHandle

	
	
}// end class
