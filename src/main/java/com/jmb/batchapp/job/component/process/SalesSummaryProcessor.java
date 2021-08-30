package com.jmb.batchapp.job.component.process;


import static org.apache.spark.sql.functions.col;

import com.jmb.batchapp.configuration.SparkSessionProvider;
import com.jmb.batchapp.job.component.transformation.reducer.SellerSalesReducer;
import com.jmb.batchapp.job.context.SalesSummaryContext;
import com.jmb.batchapp.parameter.SalesSummaryJobParams;
import com.jmb.batchapp.schema.SalesSchema;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SalesSummaryProcessor implements Processor<Dataset<Row>>{

    @Autowired
    private SalesSummaryContext salesSummaryContext;

    @Override
    public Dataset<Row> process(Dataset<Row> input) {
        String[] sellers = salesSummaryContext.getParams().getParamValue(SalesSummaryJobParams.SELLER_ID).split(",");
        List<Row> results = Arrays.stream(sellers).map(seller -> {
                String sellerId = seller.replaceAll("'", "");
                Dataset<Row> sellerRows = input.filter(col(SalesSchema.SELLER_ID.name()).equalTo(sellerId));
                return sellerRows.reduce(new SellerSalesReducer());
        }).collect(Collectors.toList());
        return SparkSessionProvider.provideSession(salesSummaryContext.getSparkMode())
                .createDataFrame(results, input.schema());
    }
}
