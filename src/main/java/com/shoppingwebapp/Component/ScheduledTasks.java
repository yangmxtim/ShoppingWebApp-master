package com.shoppingwebapp.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shoppingwebapp.Service.DataProcessorService;

@Component
public class ScheduledTasks {

    @Autowired
    private DataProcessorService dataProcessorService;

    @Scheduled(fixedDelay = 3600000) // 1hour執行一次
    public void scheduleTaskWithFixedDelay() {
        dataProcessorService.processAndUpdateDataChart3();
        dataProcessorService.processAndUpdateDataChart4();
        dataProcessorService.processAndUpdateDataChart5();
    }
}
