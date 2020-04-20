package com.sell.modules.sys.security;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;
/**
 * ServerEndpoint注解相当于设置访问URL,传过来的id作为连接的唯一标识，
 * 约定普通用户为userId,商家shopId,骑手deliveryId
 * @author linyuc
 * @date 2020/3/10 11:37
 */
@Component
@ServerEndpoint("/websocket/{id}")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets = new CopyOnWriteArraySet<>();
    private static Map<String, Session> sessionPool = new HashMap<String, Session>();

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "id") String id) {
        this.session = session;
        webSockets.add(this);
        sessionPool.put(id, session);
        System.out.println("【websocket消息】用户："+id+" 建立了新的连接，总数为:" + webSockets.size());
    }

    @OnClose
    public void onClose() {
        webSockets.remove(this);
        System.out.println("【websocket消息】连接断开，总数为:" + webSockets.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket消息】收到客户端消息:" + message);
    }

    // 此为广播消息
    public void sendAllMessage(String message) {
        for (WebSocket webSocket : webSockets) {
            System.out.println("【websocket消息】广播消息:" + message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息
    public void sendOneMessage(String id, String message) {

        Session session = sessionPool.get(id);
        if (session != null) {
            try {
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}