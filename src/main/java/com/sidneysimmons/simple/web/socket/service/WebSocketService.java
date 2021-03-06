package com.sidneysimmons.simple.web.socket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sidneysimmons.simple.web.socket.domain.Message;
import com.sidneysimmons.simple.web.socket.domain.SystemMessage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PongMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Service class that acts as a web socket handler.
 * 
 * @author Sidney Simmons
 */
@Slf4j
@Service("webSocketService")
public class WebSocketService extends TextWebSocketHandler {

    @Resource(name = "objectMapper")
    private ObjectMapper objectMapper;

    private List<WebSocketSession> sessions = new ArrayList<>();

    /**
     * Add the new session to the list of sessions.
     */
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

    }

    /**
     * Remove the session from the list of sessions.
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("Connection closed from " + session.getRemoteAddress() + ".");
        removeSession(session);
    }

    /**
     * Synchronized method for sending a given message to all web socket sessions. Note that this
     * synchronized approach is not efficient - this is just an example.
     * 
     * @param message the message
     */
    public synchronized void sendMessage(Message message) {
        // Turn the message object into a payload
        String messagePayload = null;
        try {
            messagePayload = objectMapper.writeValueAsString(message);
        } catch (IOException e) {
            log.warn("Cannot create message payload. Message will not be sent.", e);
            return;
        }

        // Send the message payload to all sessions
        TextMessage textMessage = new TextMessage(messagePayload);
        for (WebSocketSession session : sessions) {
            try {
                session.sendMessage(textMessage);
            } catch (IOException e) {
                log.warn("Cannot send message to session " + session.getRemoteAddress() + ".", e);
            }
        }
    }

    /**
     * Synchronized method for adding a given session to the list of sessions. Note that this
     * synchronized approach is not efficient - this is just an example.
     * 
     * @param session the session
     */
    private synchronized void addSession(WebSocketSession session) {
        sessions.add(session);

        // Send a notification that a session was added
        Message message = new SystemMessage();
        message.setMessage(session.getRemoteAddress() + " joined.");
        sendMessage(message);
    }

    /**
     * Synchronized method for removing a given session from the list of sessions. Note that this
     * synchronized approach is not efficient - this is just an example.
     * 
     * @param session the session
     */
    private synchronized void removeSession(WebSocketSession session) {
        sessions.remove(session);

        // Send a notification that a session was removed
        Message message = new SystemMessage();
        message.setMessage(session.getRemoteAddress() + " left.");
        sendMessage(message);
    }

}
