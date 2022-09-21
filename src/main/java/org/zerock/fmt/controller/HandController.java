package org.zerock.fmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.fmt.domain.BuyDTO;
import org.zerock.fmt.domain.HandVO;
import org.zerock.fmt.exception.ServiceException;
import org.zerock.fmt.service.BuyService;
import org.zerock.fmt.service.HandService;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@RequestMapping("/hand")	//기본URI(Base URI)
@Controller
public class HandController {
	
	
	@Setter(onMethod_= {@Autowired})
	private HandService handService;
	
	@Setter(onMethod_= {@Autowired})
	private BuyService buyService;
	
	
	@GetMapping("/buyHands")
	public String buyHands(Model model) throws ServiceException {
		log.trace("buyHands() invoked.");
		
		try {
			List<HandVO> list = this.handService.getAllHands();
			list.forEach(log::trace);
			
			model.addAttribute("_BuyHands_", list);
			
			return "hand/4-01_buyHands";
		} catch(Exception e) {
			throw new ServiceException(e);
		}
		
	} // buyHands
	
	

	@GetMapping("/payPage")
	public String payPage(Integer h_number, Model model) throws ServiceException {
		log.trace("payPage() invoked.");
		
		try {
			HandVO vo = this.handService.getHand(h_number);
			
			model.addAttribute("_HandVO_", vo);
			
			log.info("\t+ _HandVO_: {}", vo);
			
			return "hand/4-02_payPage";
		} catch(Exception e) {
			throw new ServiceException(e);
		}
		
	} // payPage
	

	@PostMapping("/payPage")
	public void buy(BuyDTO dto) throws ServiceException {
		
		BuyDTO insertBuyHands = new BuyDTO("leey@gmail.com", 2, 1, 6600);
		
		int result = this.buyService.buy(dto);
		
		log.info("\t+ result: {}", result);
		
	} // buyHands
	

}//end class