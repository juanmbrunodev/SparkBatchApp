package com.jmb.batchapp.dictionary;

/**
 * Class acts as a placeholder for {@link com.jmb.batchapp.job.implementation.IngesterJob} related queries
 *
 * @author JuanMBruno.
 */
public class IngesterQueries {

    public static String SALES_TABLE = "SALES";

    public static String INSERT_QUERY_SALES = "INSERT INTO " + SALES_TABLE + " (SELLER_ID, SALES_DATE, PRODUCT, QUANTITY)" +
            " VALUES (?, ?, ?, ?)";
}
