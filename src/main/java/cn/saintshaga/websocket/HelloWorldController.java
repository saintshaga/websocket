package cn.saintshaga.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@Slf4j
public class HelloWorldController {


	@Autowired
	private WebsocketConnectionManager connectionManager;

	@RequestMapping("/")
	public String home() {
		return "Hello, World!";
	}

	@RequestMapping("/test1")
	public String test1(@PathParam("id") String id) {
		connectionManager.closeSession(id);

		log.info("Session is Open:{}", connectionManager.getSession(id) != null && connectionManager.getSession(id).isOpen());
		return "this is test1";
	}
}
