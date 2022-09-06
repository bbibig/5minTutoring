package org.zerock.fmt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.fmt.domain.AdminVO;
import org.zerock.fmt.domain.UserVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@Component
public class AdminLoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		log.trace("AdminLogin preHandle invoked.");
		
		HttpSession session = req.getSession();
		session.removeAttribute("_ADMIN_");
		log.info("\t + LoginInterceptor : Session remove.");
		
		return true;
	}//preHandle
	
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.trace("AdminLogin postHandler invoked.");
		
		ModelMap modelMap = modelAndView.getModelMap();
		AdminVO admin = (AdminVO) modelMap.getAttribute("_ADMIN_");
		log.info("\t+ loginVO for Session : {}", admin);
		//로그인 된 유저 SessionScope에 바인딩 
		req.getSession().setAttribute("_ADMIN_", admin);
//		req.getSession().setAttribute("_ADMIN_RESULT_","님, 로그인 하였습니다.");
//		res.sendRedirect("/login/home");
		
	}//postHandle

	
	
}// end class
