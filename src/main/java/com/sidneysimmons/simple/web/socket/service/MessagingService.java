package com.sidneysimmons.simple.web.socket.service;

import com.sidneysimmons.simple.web.socket.domain.BroadcastMessage;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("messagingService")
public class MessagingService {

    @Resource(name = "webSocketService")
    private WebSocketService webSocketService;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss zzz")
            .withZone(ZoneId.systemDefault());

    @Scheduled(fixedDelayString = "${system.broadcast.message.delay}")
    private void sendSystemBroadcastMessage() {
        BroadcastMessage message = new BroadcastMessage();
        message.setMessage("System broadcast on " + DATE_TIME_FORMATTER.format(Instant.now()) + ".");
        webSocketService.broadcastMessage(message);
    }

}
