package com.jmb.batchapp.job.component.write;

import com.jmb.batchapp.job.component.transformation.mapper.IngesterSalesToDbMapper;
import com.jmb.batchapp.persistence.manager.DatabaseConnManager;
import com.jmb.batchapp.schema.SalesSchema;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class represents a component with functionality to persists DataFrame rows to a Database Table.
 *
 * @author JuanMBruno.
 */
@Component
public class IngesterWriter implements Writer<Dataset<Row>>{

    private static Logger LOGGER = LoggerFactory
            .getLogger(IngesterWriter.class);

    @Autowired
    private DatabaseConnManager connManager;

    @Override
    public void write(Dataset<Row> input) {
        LOGGER.info("Writing Records to SALES table in the DB...");
        Dataset<Row> persistedDf = input.mapPartitions(new IngesterSalesToDbMapper(connManager),
                RowEncoder.apply(SalesSchema.getSparkSchema()));
        //Call a Spark Action triggering all the previous transformations done
        persistedDf.count();
    }
}
