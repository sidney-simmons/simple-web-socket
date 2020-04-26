package com.sidneysimmons.simple.web.socket.service;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@Service("webSocketService")
public class WebSocketService extends TextWebSocketHandler {

    private List<WebSocketSession> sessions = new ArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Connection established from " + session.getRemoteAddress() + ".");
        addSession(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

    }

    @Override
    public void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {

    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        log.info("Transport error occurred for " + session.getRemoteAddress() + ".", exception);
        removeSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Connection closed from " + session.getRemoteAddress() + ".");
        removeSession(session);
    }

    private synchronized void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    private synchronized void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

}
