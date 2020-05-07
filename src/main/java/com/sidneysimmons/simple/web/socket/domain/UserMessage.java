package com.sidneysimmons.simple.web.socket.domain;

/**
 * User web socket message.
 * 
 * @author Sidney Simmons
 */
public class UserMessage extends Message {

    @Override
    public MessageType getMessageType() {
        return MessageType.USER;
    }

}
