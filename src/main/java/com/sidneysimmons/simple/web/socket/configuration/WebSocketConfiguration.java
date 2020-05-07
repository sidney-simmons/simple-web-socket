package com.sidneysimmons.simple.web.socket.configuration;

import com.sidneysimmons.simple.web.socket.service.WebSocketService;
import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Web socket configuration.
 * 
 * @author Sidney Simmons
 */
@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    @Resource(name = "webSocketService")
    private WebSocketService webSocketService;

    /**
     * Register a web socket handler under a specific URL.
     */
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(webSocketService, "/web-socket");
    }

}