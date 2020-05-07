package com.sidneysimmons.simple.web.socket.domain;

/**
 * Enumeration for web socket message types.
 * 
 * @author Sidney Simmons
 */
public enum MessageType {

    /** System generated message. */
    SYSTEM(),

    /** User generated message. */
    USER();

    private MessageType() {
        // This enumeration doesn't have any internal data yet
    }

}