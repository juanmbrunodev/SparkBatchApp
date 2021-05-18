package com.jmb.batchapp.job.context;

import com.jmb.batchapp.parameter.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class IngesterJobContext extends BaseJobContext implements Serializable {

    public static final String CSV_FORMAT = "csv";
    public static final String JSON_FORMAT = "json";

    @Autowired
    private Parameters params;

    //Job Specific configurations and other info used can go here
    public Parameters getParams() {
        return params;
    }

    public void setParams(Parameters params) {
        this.params = params;
    }
}
