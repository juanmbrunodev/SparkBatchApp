package com.jmb.batchapp.job.component.transformation.mapper;

import com.jmb.batchapp.dictionary.IngesterQueries;
import com.jmb.batchapp.persistence.manager.DatabaseConnManager;
import com.jmb.batchapp.schema.SalesSchema;
import org.apache.spark.api.java.function.MapPartitionsFunction;
import org.apache.spark.sql.Row;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * Class implements Spark API functionality to persist spark rows corresponding to SALES table to the database.
 *
 * @author JuanMBruno
 */
public class IngesterSalesToDbMapper implements MapPartitionsFunction<Row, Row> {

    private DatabaseConnManager connManager;

    public IngesterSalesToDbMapper(DatabaseConnManager connManager) {
        this.connManager = connManager;
    }

    @Override
    public Iterator<Row> call(Iterator<Row> iterator) throws Exception {
        return doRowsBatchInsert(iterator);
    }

    private Iterator<Row> doRowsBatchInsert(Iterator<Row> iterator) throws SQLException {
        try (Connection connection = connManager.getDataSource().getConnection();
             PreparedStatement stmt = connection.prepareStatement(IngesterQueries.INSERT_QUERY_SALES)) {
           //Loop through DataFrame's received rows within the iterator and persist to DB
            while(iterator.hasNext()) {
                int index = 1;
                Row row = iterator.next();
                stmt.setString(index++, row.getAs(SalesSchema.SELLER_ID.name()));
                stmt.setDate(index++, row.getAs(SalesSchema.DATE.name()));
                stmt.setString(index++, row.getAs(SalesSchema.PRODUCT.name()));
                stmt.setLong(index++, row.getAs(SalesSchema.QUANTITY.name()));
                stmt.addBatch();
            }
            //Once all the rows have been added as batch statements, execute against the DB
            stmt.executeBatch();
        } catch (SQLException sqlException) {
            //Do some logging here and re throw Exception
            throw sqlException;
        }
        return iterator;
    }
}
