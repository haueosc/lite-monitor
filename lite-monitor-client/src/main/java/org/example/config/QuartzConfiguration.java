package org.example.config;

import jakarta.annotation.Resource;
import org.example.entity.ConnectionConfig;
import org.example.task.MonitorJobBean;
import org.example.utils.CronUtils;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfiguration {

    @Resource
    ServerConfiguration serverConfiguration;

    public JobDetail jobDetailFactoryBean() {
        return JobBuilder.newJob(MonitorJobBean.class)
                .withIdentity("monitor-runtime-task")
                .storeDurably().build();
    }

    public Trigger cronTriggerFactoryBean() throws InterruptedException {
        ConnectionConfig configurationFromFile = serverConfiguration.getConfigurationFromFile();
        CronScheduleBuilder cron = CronScheduleBuilder.cronSchedule(CronUtils.generateCron(configurationFromFile.getCron()));
        return TriggerBuilder.newTrigger()
                .withIdentity("monitor-runtime-trigger")
                .withSchedule(cron)
                .build();
    }
}
