package org.zerock.fmt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.zerock.fmt.domain.UserVO;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor

@Component
public class ChattingService extends TextWebSocketHandler {

	private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		
		log.info("#ChattingHandler, afterConnectionEstablished");
		sessionList.add(session);
		
		log.info("session: {}", session);
		log.info(session.getId() + "님이 입장하셨습니다.");
		log.info(session.getAttributes());
		
	} // afterConnectionEstablished
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		
		log.info("#ChattingHandler, handleMessage");
		log.info(session.getId() + ": " + message);
		
		UserVO userVO = (UserVO) session.getAttributes().get("__LOGIN_USER__");
		String userNick = userVO.getUser_nick();
		
		for(WebSocketSession s : sessionList) {
			s.sendMessage(new TextMessage(userNick + ":" + message.getPayload()));
		}
	} // handleTextMessage
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		
		log.info("#ChattingHandler, afterConnectionClosed");

		sessionList.remove(session); // 세션 제거
		
		log.info(session.getId() + "님이 퇴장하셨습니다.");
	} // afterConnectionClosed
	
} // end class
