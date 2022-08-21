package org.zerock.fmt.controller;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor
@Log4j2
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"file:src/main/webapp/**/*-context.xml"})

@WebAppConfiguration
public class LoginControllerTests {
	
	@Autowired
	private WebApplicationContext ctx;
	
	@Test
	@Order(1)
	@Timeout(value = 5, unit = TimeUnit.SECONDS)
	void signUpStudentTest() throws Exception {
		log.info("signUpStudentTest-----학생회원가입");
		
		
		MockMvcBuilder mvcBulider = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mvcBulider.build();
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/login/signUp_student");
		reqBuilder.param("user_email", "학생가입");
		reqBuilder.param("user_pw", "11111");
		reqBuilder.param("user_nick", "닉네임1");
		reqBuilder.param("user_name", "이름1");
		reqBuilder.param("user_birth", "19990000");
		reqBuilder.param("user_gender", "여자");
		reqBuilder.param("user_phone", "010-1111-0000");
		reqBuilder.param("st_school", "중학생");
		reqBuilder.param("st_grade", "1학년");

		ModelAndView modelAndView = mockMvc.perform(reqBuilder).
									andReturn().getModelAndView();
		
		log.info("\t modelAndView : {}", modelAndView);
		
	}//signUpStudentTest
}//end class
