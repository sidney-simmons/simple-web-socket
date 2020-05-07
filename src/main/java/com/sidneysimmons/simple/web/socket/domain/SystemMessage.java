package com.sidneysimmons.simple.web.socket.domain;

public class SystemMessage extends Message {

    @Override
    public MessageType getMessageType() {
        return MessageType.SYSTEM;
    }

}
