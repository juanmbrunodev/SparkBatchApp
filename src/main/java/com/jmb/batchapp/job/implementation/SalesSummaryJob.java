package com.jmb.batchapp.job.implementation;

import com.jmb.batchapp.job.Job;
import com.jmb.batchapp.job.component.process.Processor;
import com.jmb.batchapp.job.component.read.Reader;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class SalesSummaryJob extends Job<Dataset<Row>> {

    private static Logger LOGGER = LoggerFactory
            .getLogger(SalesSummaryJob.class);

    private Reader salesSummaryReader;
    private Processor salesSummaryProcessor;

    @Autowired
    public SalesSummaryJob(@Qualifier("salesSummaryReader") Reader salesSummaryReader,
                           @Qualifier("salesSummaryProcessor") Processor salesSummaryProcessor) {
        this.salesSummaryReader = salesSummaryReader;
        this.salesSummaryProcessor = salesSummaryProcessor;
    }

    @Override
    protected Dataset<Row> preProcess() {
        return (Dataset<Row>) salesSummaryReader.read();
    }

    @Override
    protected Dataset<Row> process(Dataset<Row> preProcessOutput) {
        return (Dataset<Row>) salesSummaryProcessor.process(preProcessOutput);
    }

    @Override
    protected void postProcess(Dataset<Row> processOutput) {
        LOGGER.info("Results for the Job Execution");
        processOutput.show();
    }
}
