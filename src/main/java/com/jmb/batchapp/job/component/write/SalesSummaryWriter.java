package com.jmb.batchapp.job.component.write;

import com.jmb.batchapp.job.component.transformation.mapper.SellerSalesDBMapper;
import com.jmb.batchapp.persistence.manager.DatabaseConnManager;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.encoders.RowEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Writer class for the {@link com.jmb.batchapp.job.implementation.SalesSummaryJob} which persists
 * the rows of the received DataFrame into the SALES_SUMMARY DB table.
 * For this it calls Spark Mapper class.
 *
 * @author JuanMBruno.
 */
@Component
public class SalesSummaryWriter implements Writer<Dataset<Row>>{

    private static final Logger LOGGER = LoggerFactory.getLogger(SalesSummaryWriter.class);

    private DatabaseConnManager databaseConnManager;

    @Autowired
    public SalesSummaryWriter(DatabaseConnManager databaseConnManager) {
        this.databaseConnManager = databaseConnManager;
    }

    @Override
    public void write(Dataset<Row> input) {
        Dataset<Row> processedDataset = input.mapPartitions(new SellerSalesDBMapper(databaseConnManager),
                RowEncoder.apply(input.schema()));
        LOGGER.info("Total Amount of Rows Processed in SalesSummaryWriter" + processedDataset.count());
    }
}
