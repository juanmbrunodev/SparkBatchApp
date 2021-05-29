package com.jmb.batchapp.job.component.transformation.mapper;

import com.jmb.batchapp.dictionary.SalesSummaryQueries;
import com.jmb.batchapp.persistence.manager.DatabaseConnManager;
import com.jmb.batchapp.schema.SalesSummarySchema;
import org.apache.spark.api.java.function.MapPartitionsFunction;
import org.apache.spark.sql.Row;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * Class persists the received rows into the SALES_SUMMARY DB table, making a translation between
 * the Row's Spark Schema and the Table structure as needed.
 *
 * @author JuanMBruno.
 */
public class SellerSalesDBMapper implements MapPartitionsFunction<Row, Row> {

    private DatabaseConnManager connManager;

    public SellerSalesDBMapper(DatabaseConnManager connManager) {
        this.connManager = connManager;
    }

    @Override
    public Iterator<Row> call(Iterator<Row> rowIterator) throws Exception {
        return doRowsBatchInsert(rowIterator);
    }

    private Iterator<Row> doRowsBatchInsert(Iterator<Row> rowIterator) throws Exception {
        try (Connection connection = connManager.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(SalesSummaryQueries.INSERT_SALES_SUMMARY)) {
            //Loop through DataFrame's received rows within the iterator and persist to DB
            while(rowIterator.hasNext()) {
                int index = 1;
                Row row = rowIterator.next();
                stmt.setString(index++, row.getAs(SalesSummarySchema.SELLER_ID.name()));
                stmt.setDate(index++, row.getAs(SalesSummarySchema.SALES_DATE.name()));
                stmt.setString(index++, row.getAs(SalesSummarySchema.PRODUCT.name()));
                stmt.setInt(index++, row.getAs(SalesSummarySchema.QUANTITY.name()));
                stmt.addBatch();
            }
            //Once all the rows have been added as batch statements, execute against the DB
            stmt.executeBatch();
        } catch (SQLException sqlException) {
            //Do some logging here and re throw Exception
            throw sqlException;
        }
        return rowIterator;
    }
}
