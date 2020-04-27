package com.sidneysimmons.simple.web.socket.service;

import com.sidneysimmons.simple.web.socket.domain.BroadcastMessage;
import java.time.Instant;
import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("messagingService")
public class MessagingService {

    @Resource(name = "webSocketService")
    private WebSocketService webSocketService;

    @Scheduled(fixedDelayString = "${system.broadcast.message.delay}")
    private void sendSystemBroadcastMessage() {
        BroadcastMessage message = new BroadcastMessage();
        message.setMessage("System broadcast - " + Instant.now());
        webSocketService.broadcastMessage(message);
    }

}
