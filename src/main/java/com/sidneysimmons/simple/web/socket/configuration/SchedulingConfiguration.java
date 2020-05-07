package com.sidneysimmons.simple.web.socket.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Scheduling configuration.
 * 
 * @author Sidney Simmons
 */
@Configuration
@EnableScheduling
public class SchedulingConfiguration {

    /**
     * Create a task scheduler for spring to execute methods annotated with the "@Scheduled" annotation.
     * 
     * @return a task scheduler
     */
    @Bean(name = "taskScheduler")
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(5);
        taskScheduler.setThreadNamePrefix("custom-task-scheduler-");
        return taskScheduler;
    }

}
