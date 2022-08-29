package org.zerock.fmt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.zerock.fmt.common.SharedScopeKeys;
import org.zerock.fmt.domain.UserVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@Component
public class Logoutinterceptor implements HandlerInterceptor{
	
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
			throws Exception {
		log.trace("LogOut interceptor invoked.");
		
		UserVO loginVO = (UserVO) req.getSession().getAttribute(SharedScopeKeys.LOGIN_USER);
		log.info("\t Logout User : {}", loginVO);
		req.getSession().invalidate();
		res.sendRedirect("/");
		return false;
	}//preHandle

}//end class
