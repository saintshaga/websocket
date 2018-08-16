package cn.saintshaga.websocket;

import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

/**
 * Created by huang on 18-8-15.
 */
@Service
public class WebsocketConnectionManager {
    private Map<String, WebSocketSession> connections = Maps.newConcurrentMap();

    public WebSocketSession getSession(String sessionId) {
        return connections.get(sessionId);
    }

    public void addSession(WebSocketSession session) {
        connections.put(session.getId(), session);
    }

    public boolean removeSession(WebSocketSession session) {
        if(session.getId() == null || !connections.containsKey(session.getId())) {
            return false;
        }
        connections.remove(session.getId());
        return true;
    }

    public void closeSession(String sessionId) {
        WebSocketSession session = getSession(sessionId);
        if(session != null) {
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
