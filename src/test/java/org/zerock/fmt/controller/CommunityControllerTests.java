package org.zerock.fmt.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations= {"file:src/main/webapp/**/spring/**/*-context.xml"})
@WebAppConfiguration

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommunityControllerTests {
	
	@Autowired
	private WebApplicationContext ctx;
	
	@BeforeAll
	void injectionTest() {
		log.trace("injectionTest() invoked.");
		
		assertNotNull(this.ctx);
		log.info("\t + ctx: {}", this.ctx);
	} 

	
//	@Disabled
	@Test
	@Order(1)
	@DisplayName("1. CommunityService.selectAllList() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	void testSelectAllList() throws Exception {
		
		MockMvcBuilder mockMvcBuilder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = mockMvcBuilder.build();
		
		RequestBuilder reqBuilder = MockMvcRequestBuilders.get("/community");
		
		ModelMap modelMap = 
			mockMvc.
				perform(reqBuilder).
				andReturn().
				getModelAndView().
				getModelMap();
		
		modelMap.clear();
		modelMap = null;
	}// testSelectAllList
	
	
//	@Disabled
	@Test
	@Order(2)
	@DisplayName("2. CommunityService.register() test.")
	@Timeout(value=3, unit=TimeUnit.SECONDS)
	public void testRegister() throws Exception {
		log.trace("testGetList() invoked.");
		
		MockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(ctx);
		MockMvc mockMvc = builder.build();
		
//		----
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/community");
		// 필요한 전송파라미터를 설정(Lvalue의 타입으로, 구현타입으로 지정해야 만 함)
		reqBuilder.param("title", "TITLE_NEW");
		reqBuilder.param("content", "CONTENT_NEW");
		reqBuilder.param("writer", "WRITER_NEW");
		
//		----
		
		String viewName = 
			mockMvc.
				perform(reqBuilder).
				andReturn().
				getModelAndView().
				getViewName();
		
		log.info("\t+ viewName: {}", viewName);
	} // testRegister
	
}// end class
