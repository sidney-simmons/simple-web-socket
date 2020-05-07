package com.sidneysimmons.simple.web.socket.domain;

import lombok.Data;

/**
 * Abstract web socket message.
 * 
 * @author Sidney Simmons
 */
@Data
public abstract class Message {

    private String message;

    public abstract MessageType getMessageType();

}
