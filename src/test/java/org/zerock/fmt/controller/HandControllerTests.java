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

@Log4j2
@NoArgsConstructor

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/**/*-context.xml"})

@WebAppConfiguration
public class HandControllerTests {

	@Autowired
	private WebApplicationContext ctx;
	
	@Test
	@Order(1)
	@Timeout(value = 10, unit = TimeUnit.SECONDS)
	void buyHands() throws Exception {
		log.trace("buyhands() invoked.");
		
		MockMvcBuilder mvcBulider = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mvcBulider.build();
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/hand/buyHands");
		
		ModelAndView modelAndView = 
				mockMvc.
					perform(reqBuilder).
					andReturn().
					getModelAndView();

		log.info("\t modelAndView : {}", modelAndView);
		
	}
	
	@Test
	@Order(2)
	@Timeout(value = 10, unit = TimeUnit.SECONDS)
	void payPage() throws Exception {
		log.trace("payPage() invoked.");
		
		MockMvcBuilder mvcBulider = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mvcBulider.build();
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/hand/payPage");
		
		ModelAndView modelAndView = 
				mockMvc.
					perform(reqBuilder).
					andReturn().
					getModelAndView();

		log.info("\t modelAndView : {}", modelAndView);
	}
	
	@Test
	@Order(3)
	@Timeout(value = 10, unit = TimeUnit.SECONDS)
	void buy() throws Exception {
		log.trace("buy() invoked.");
		
		MockMvcBuilder mvcBulider = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mvcBulider.build();
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/hand/payPage");
		reqBuilder.param("user_email", "test@gmail.com");
		reqBuilder.param("h_number", "4");
		reqBuilder.param("b_count", "1");
		reqBuilder.param("b_price", "19800");
		
		
		ModelAndView modelAndView = 
				mockMvc.
					perform(reqBuilder).
					andReturn().
					getModelAndView();

		log.info("\t modelAndView : {}", modelAndView);
	} // buy
	
	
	
} // end class
