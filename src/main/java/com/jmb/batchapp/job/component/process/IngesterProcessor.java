package com.jmb.batchapp.job.component.process;

import com.jmb.batchapp.job.component.transformation.flatmapper.IngesterJsonFlatMapper;
import com.jmb.batchapp.schema.SalesSchema;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class IngesterProcessor implements Processor<Dataset<Row>> {

    private static Logger LOGGER = LoggerFactory
            .getLogger(IngesterProcessor.class);

    @Override
    public Dataset<Row> process(Dataset<Row> inputDf) {
        LOGGER.info("Flattening JSON records...");
        //Get the appropriate Spark based class doing a Transformation
        Dataset<Row> parsedResults = inputDf.flatMap(new IngesterJsonFlatMapper(), RowEncoder.apply(SalesSchema.getSparkSchema()));
        return parsedResults;
    }
}
