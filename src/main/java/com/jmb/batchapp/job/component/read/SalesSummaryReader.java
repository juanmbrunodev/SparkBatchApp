package com.jmb.batchapp.job.component.read;

import com.jmb.batchapp.configuration.SparkSessionProvider;
import com.jmb.batchapp.dictionary.SalesSummaryQueries;
import com.jmb.batchapp.job.context.SalesSummaryContext;
import com.jmb.batchapp.parameter.SalesSummaryJobParams;
import com.jmb.batchapp.persistence.manager.DatabaseConnManager;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Reader class implementation for the {@link com.jmb.batchapp.job.implementation.SalesSummaryJob}
 *
 * @author JuanMBruno
 */
@Component
public class SalesSummaryReader implements Reader<Dataset<Row>> {

    @Autowired
    private SalesSummaryContext salesSummaryContext;

    private DatabaseConnManager databaseConnManager;

    @Autowired
    public SalesSummaryReader(DatabaseConnManager databaseConnManager) {
        this.databaseConnManager = databaseConnManager;
    }

    //Read using SparkSQL component, DataFrameReader class, fires a query to the SALES table
    @Override
    public Dataset<Row> read() {
        String sellerId = salesSummaryContext.getParams().getParamValue(SalesSummaryJobParams.SELLER_ID);
        String date = salesSummaryContext.getParams().getParamValue(SalesSummaryJobParams.DATE);
        String product = salesSummaryContext.getParams().getParamValue(SalesSummaryJobParams.PRODUCT);
        //Replace params passed and construct query
        String salesRetrieveQuery = String.format(SalesSummaryQueries.SALES_PER_SELLER_ID_DATE_PROD, sellerId, date, product);
        return getDatasetFromDbTable(salesRetrieveQuery);
    }

    private Dataset<Row> getDatasetFromDbTable(String query) {
        Properties properties = new Properties();
        properties.put("user", databaseConnManager.getUsername());
        properties.put("password", databaseConnManager.getPassword());
        return SparkSessionProvider.provideSession(salesSummaryContext.getSparkMode())
                .read().jdbc(databaseConnManager.getDbUrl(), "(" + query + ") sales_alias", properties);
    }
}
