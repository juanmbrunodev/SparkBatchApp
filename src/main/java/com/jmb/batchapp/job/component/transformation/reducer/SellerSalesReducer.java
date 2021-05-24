package com.jmb.batchapp.job.component.transformation.reducer;

import org.apache.spark.api.java.function.ReduceFunction;
import org.apache.spark.sql.Row;

/**
 * Reducer class implements Spark's ReduceFunction and belongs to {@link com.jmb.batchapp.job.implementation.SalesSummaryJob}
 * In doing so it 'reduces' with a sum operation all the sales for a seller, item and particular date.
 *
 * @author JuanMBruno.
 */
public class SellerSalesReducer implements ReduceFunction<Row> {

    @Override
    public Row call(Row aggregatedRow, Row currentRow) throws Exception {
        //Implement your solution here!
        return aggregatedRow;
    }
}
