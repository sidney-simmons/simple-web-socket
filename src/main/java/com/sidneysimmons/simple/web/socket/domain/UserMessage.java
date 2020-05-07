package com.sidneysimmons.simple.web.socket.domain;

public class UserMessage extends Message {

    @Override
    public MessageType getMessageType() {
        return MessageType.USER;
    }

}
