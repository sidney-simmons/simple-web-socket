package com.sidneysimmons.simple.web.socket.controller;

import com.sidneysimmons.simple.web.socket.domain.UserMessage;
import com.sidneysimmons.simple.web.socket.service.WebSocketService;
import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for interacting with the web sockets.
 * 
 * @author Sidney Simmons
 */
@RestController
@RequestMapping(value = "/web-socket", produces = MediaType.APPLICATION_JSON_VALUE)
public class WebSocketController {

    @Resource(name = "webSocketService")
    private WebSocketService webSocketService;

    @PostMapping(value = "/submit-message")
    public void submitMessage(@RequestBody UserMessage message) throws IOException {
        webSocketService.sendMessage(message);
    }

}
