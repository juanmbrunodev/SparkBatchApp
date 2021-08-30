package com.jmb.batchapp.job.component.transformation.flatmapper;

import com.jmb.batchapp.schema.SalesSchema;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import org.apache.spark.sql.types.StructType;
import scala.collection.mutable.WrappedArray;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class uses a FlatMapFunction transformation to expand and parse JSON records into the desired
 * SALES TABLE records structure.
 *
 * @author JuanMBruno
 */
public class IngesterJsonFlatMapper implements FlatMapFunction<Row, Row> {

    private static final String SELLER_COLUMN = "Seller_Id";
    private static final String SALES_COLUMN = "Sales";

    private static final StructType salesSchema = SalesSchema.getSparkSchema();

    @Override
    public Iterator<Row> call(Row row) throws Exception {
        return explodeSalesPerSellerIntoRows(row).iterator();
    }

    private List<Row> explodeSalesPerSellerIntoRows(Row inputRow) {
        List<Row> explodedRows = new LinkedList<>();
        //First, retrieve the Seller for the sales included in the row
        String sellerId = inputRow.getAs(SELLER_COLUMN);
        //Get the sales array for a Seller
        WrappedArray<String> salesArray = inputRow.getAs(SALES_COLUMN);
        Row[] sales = (Row[]) salesArray.array();
        for(Row row : sales) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            //Retrieve the date for a sales object first
            Date saleDate = Date.valueOf(LocalDate.parse((String) row.getAs("Date"), formatter));
            WrappedArray<Row> items = row.getAs("Items");
            for(Row itemRow: (Row[]) items.array()) {
                int valuesIndex = 0;
                Object[] rowValues = new Object[4];
                rowValues[valuesIndex++] = sellerId;
                rowValues[valuesIndex++] = saleDate;
                rowValues[valuesIndex++] = itemRow.getAs("Product");
                rowValues[valuesIndex++] = itemRow.getAs("Quantity");
                explodedRows.add(new GenericRowWithSchema(rowValues, salesSchema));
            }
        }
        return explodedRows;
    }
}
