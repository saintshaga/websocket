package cn.saintshaga.websocket.handler;

import cn.saintshaga.websocket.WebsocketConnectionManager;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;

public class MyWebSocketHandler extends TextWebSocketHandler {

	private Map<String, WebSocketSession> allWebsockets;

	public MyWebSocketHandler() {
		allWebsockets = Maps.newConcurrentMap();
	}

	@Autowired
	private WebsocketConnectionManager connectionManager;

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		String messageContent = message.getPayload();
		System.out.println(messageContent);
		if(!Strings.isNullOrEmpty(messageContent) && messageContent.contains("wait")) {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Finish handling message:" + messageContent);
		try {
			session.sendMessage(new TextMessage("I have received:" + messageContent));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		allWebsockets.put(session.getId(),session);
		connectionManager.addSession(session);
		System.out.println("started:" + getSessionInfo(session));
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		allWebsockets.remove(session.getId());
		connectionManager.removeSession(session);
		System.out.println("stopped:" + getSessionInfo(session));
	}

	private String getSessionInfo(WebSocketSession session) {
		return session.getId() + "," + session.getRemoteAddress();
	}



}
