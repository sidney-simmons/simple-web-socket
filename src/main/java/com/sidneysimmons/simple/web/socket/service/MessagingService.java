package com.sidneysimmons.simple.web.socket.service;

import com.sidneysimmons.simple.web.socket.domain.SystemMessage;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("messagingService")
public class MessagingService {

    @Resource(name = "webSocketService")
    private WebSocketService webSocketService;

    @Value("${system.recurring.message.enabled}")
    private Boolean systemRecurringMessageEnabled;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss zzz")
            .withZone(ZoneId.systemDefault());

    @Scheduled(fixedDelayString = "${system.recurring.message.delay}")
    private void sendSystemRecurringMessage() {
        if (systemRecurringMessageEnabled) {
            SystemMessage message = new SystemMessage();
            message.setMessage("System broadcast on " + DATE_TIME_FORMATTER.format(Instant.now()) + ".");
            webSocketService.sendMessage(message);
        }
    }

}
