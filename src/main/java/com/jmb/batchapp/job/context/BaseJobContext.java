package com.jmb.batchapp.job.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BaseJobContext {

    @Value("${spark.master.mode}")
    private String sparkMode;

    //Getters, setters, ect (Or use Lombok)
    public String getSparkMode() {
        return this.sparkMode;
    }
}
