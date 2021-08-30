package com.jmb.batchapp.job.context;

import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component
public class IngesterJobContext extends CommonJobContext implements Serializable {

    public static final String CSV_FORMAT = "csv";
    public static final String JSON_FORMAT = "json";

    //Job Specific configurations and other info used can go here
}
