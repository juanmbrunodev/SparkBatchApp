package com.jmb.batchapp.job.implementation;

import com.jmb.batchapp.job.Job;
import com.jmb.batchapp.parameter.Parameters;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SparkJob extends Job<Dataset<Row>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SparkJob.class);

    private Parameters parameters;

    @Autowired
    public SparkJob(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    protected Dataset<Row> preProcess() {
        //Calls component type of class
        return null;
    }

    @Override
    protected Dataset<Row> process(Dataset<Row> preProcessOutput) {
        //Calls component type of class here
        return null;
    }

    @Override
    protected void postProcess(Dataset<Row> processOutput) {
        //Other logic, implement Tidy up code, etc.
    }
}
