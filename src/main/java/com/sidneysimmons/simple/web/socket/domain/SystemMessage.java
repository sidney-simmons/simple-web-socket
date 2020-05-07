package com.sidneysimmons.simple.web.socket.domain;

/**
 * System web socket message.
 * 
 * @author Sidney Simmons
 */
public class SystemMessage extends Message {

    @Override
    public MessageType getMessageType() {
        return MessageType.SYSTEM;
    }

}
