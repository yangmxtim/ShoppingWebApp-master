package com.shoppingwebapp.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shoppingwebapp.Service.DataProcessorService;

@Component
public class ScheduledTasks {

    @Autowired
    private DataProcessorService dataProcessorService;

    @Scheduled(fixedDelay = 1200000) // 20min執行一次
    public void scheduleTaskWithFixedDelay() {
    	System.out.println("後台數據轉換");
    	dataProcessorService.processAndUpdateDataChart1();
    	dataProcessorService.processAndUpdateDataChart2();
        dataProcessorService.processAndUpdateDataChart3();
        dataProcessorService.processAndUpdateDataChart4();
        dataProcessorService.processAndUpdateDataChart5();
    }
}
