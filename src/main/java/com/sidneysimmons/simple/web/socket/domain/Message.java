package com.sidneysimmons.simple.web.socket.domain;

import lombok.Data;

@Data
public abstract class Message {

    private String message;

    public abstract MessageType getMessageType();

}
