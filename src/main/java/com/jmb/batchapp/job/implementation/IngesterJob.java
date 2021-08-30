package com.jmb.batchapp.job.implementation;

import com.jmb.batchapp.job.Job;
import com.jmb.batchapp.job.component.process.Processor;
import com.jmb.batchapp.job.component.read.Reader;
import com.jmb.batchapp.job.component.write.Writer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
public class IngesterJob extends Job<Dataset<Row>> {

    private static Logger LOGGER = LoggerFactory
            .getLogger(IngesterJob.class);

    private Reader ingesterReader;
    private Processor ingesterProcessor;
    private Writer ingesterWriter;

    @Autowired
    public IngesterJob(@Qualifier("ingesterReader") Reader<Dataset<Row>> ingesterReader,
                       @Qualifier("ingesterProcessor")Processor<Dataset<Row>> ingesterProcessor,
                       @Qualifier("ingesterWriter") Writer<Dataset<Row>> ingesterWriter ) {
        this.ingesterReader = ingesterReader;
        this.ingesterProcessor = ingesterProcessor;
        this.ingesterWriter = ingesterWriter;
    }

    @Override
    protected Dataset<Row> preProcess() {
        Dataset<Row> preProcessOutput = (Dataset<Row>) ingesterReader.read();
        return preProcessOutput;
    }

    @Override
    protected Dataset<Row> process(Dataset<Row> preProcessOutput) {
        return (Dataset<Row>) ingesterProcessor.process(preProcessOutput);
    }

    @Override
    protected void postProcess(Dataset<Row> processOutput) {
        //Persists Rows to corresponding DB table
        ingesterWriter.write(processOutput);
    }
}
