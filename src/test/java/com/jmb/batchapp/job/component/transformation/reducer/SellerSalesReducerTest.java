package com.jmb.batchapp.job.component.transformation.reducer;

import com.jmb.batchapp.schema.SalesSummarySchema;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.catalyst.expressions.GenericRowWithSchema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit Test class for {@link SellerSalesReducer}.
 *
 * @author JuanMBruno.
 */
public class SellerSalesReducerTest {

    private SellerSalesReducer reducerUnderTest;

    @BeforeEach
    public void init() {
        reducerUnderTest = new SellerSalesReducer();
    }

    @DisplayName("Test reduce rows for a seller's item sales")
    @Test
    public void testCallMethodReduceSalesForSeller() throws Exception {
        int itemSalesQuantity = 25;
        GenericRowWithSchema sampleRow = initializeSalesRow(itemSalesQuantity);
        GenericRowWithSchema anotherSampleRow = initializeSalesRow(itemSalesQuantity);
        Row expectedAggregatedRow = reducerUnderTest.call(sampleRow, anotherSampleRow);
        assertEquals(itemSalesQuantity * 2, (Integer)expectedAggregatedRow.getAs(SalesSummarySchema.QUANTITY.name()));
    }

    private GenericRowWithSchema initializeSalesRow(int quantity) {
        Object[] rowValues = new Object[4];
        int index = 0; DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        rowValues[index++] = "aSeller";
        rowValues[index++] = Date.valueOf(LocalDate.parse("1/02/2021", dateTimeFormatter));
        rowValues[index++] = "aProduct";
        rowValues[index] = quantity;
        return new GenericRowWithSchema(rowValues, SalesSummarySchema.getSparkSchema());
    }

    @AfterEach
    public void tearDown() {
        reducerUnderTest = null;
    }
}
