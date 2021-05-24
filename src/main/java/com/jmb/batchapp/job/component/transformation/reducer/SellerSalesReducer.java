package com.jmb.batchapp.job.component.transformation.reducer;

import org.apache.spark.api.java.function.ReduceFunction;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;

/**
 * Reducer class implements Spark's ReduceFunction and belongs to {@link com.jmb.batchapp.job.implementation.SalesSummaryJob}
 * In doing so it 'reduces' with a sum operation all the sales for a seller, item and particular date.
 *
 * @author JuanMBruno.
 */
public class SellerSalesReducer implements ReduceFunction<Row> {

    @Override
    public Row call(Row aggregatedRow, Row currentRow) throws Exception {
        int colsSize = aggregatedRow.schema().size();
        //Use aggregated row values and add the relevant quantity from currentRow, returning a new Row with sum.
        Object[] newAggregatedRow = new Object[colsSize];
        for (int colIndex = 0; colIndex < colsSize; colIndex++) {
            newAggregatedRow[colIndex] = aggregatedRow.get(colIndex);
        }
        int aggregatedQuantity = (Integer) newAggregatedRow[--colsSize];
        newAggregatedRow[colsSize] =  aggregatedQuantity + currentRow.getInt(colsSize);
        return new GenericRowWithSchema(newAggregatedRow, aggregatedRow.schema());
    }
}
