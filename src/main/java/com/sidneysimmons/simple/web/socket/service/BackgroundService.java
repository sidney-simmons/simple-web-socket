package com.sidneysimmons.simple.web.socket.service;

import com.sidneysimmons.simple.web.socket.domain.BackgroundColorMessage;
import java.awt.Color;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service("backgroundService")
public class BackgroundService {

    private Random random = new Random();

    @Scheduled(fixedRateString = "${background.color.update.rate}")
    private BackgroundColorMessage updateBackgroundColor() {
        // Create the new color
        Color color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
        String hexRepresentation = "#" + Integer.toHexString(color.getRGB()).substring(2);
        log.info("Updating background color to " + hexRepresentation + ".");

        // Return the new color
        BackgroundColorMessage message = new BackgroundColorMessage();
        message.setColor(hexRepresentation);
        return message;
    }

}
