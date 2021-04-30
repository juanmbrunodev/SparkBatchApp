package com.jmb.batchapp.job.implementation;

import com.jmb.batchapp.job.Job;
import com.jmb.batchapp.parameter.Parameters;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SparkJob extends Job<Dataset<Row>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SparkJob.class);

    private Parameters parameters;

    @Autowired
    public SparkJob(Parameters parameters) {
        this.parameters = parameters;
    }

    @Override
    protected Optional<Dataset<Row>> preProcess() {
        //Calls component type of class, executes Spark Transformations
        return Optional.empty();
    }

    @Override
    protected Optional<Dataset<Row>> process(Optional<Dataset<Row>> preProcessOutput) {
        //Calls component type of class here, which is the actual Spark API based Class
        return Optional.empty();
    }

    @Override
    protected void postProcess(Optional<Dataset<Row>> processOutput) {
        //Other logic, implement Tidy up code, etc.
    }
}
