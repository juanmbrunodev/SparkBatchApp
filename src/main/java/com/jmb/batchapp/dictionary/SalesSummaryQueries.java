package com.jmb.batchapp.dictionary;

public class SalesSummaryQueries {

    public static final String SALES_TABLE = "SALES";

    public static final String SALES_SUMMARY_TABLE = "SALES_SUMMARY";

    public static final String STRING_TOKEN = "%s";

    public static final String SALES_PER_SELLER_ID_DATE_PROD = "SELECT SELLER_ID, SALES_DATE, PRODUCT, QUANTITY FROM "
            + SALES_TABLE + " WHERE " + " SELLER_ID IN (" + STRING_TOKEN + ") AND SALES_DATE = PARSEDATETIME('"
            + STRING_TOKEN + "','yyyyMMddHHmmss') " + " AND PRODUCT = '" + STRING_TOKEN + "' ";

    public static final String INSERT_SALES_SUMMARY = "INSERT INTO " + SALES_SUMMARY_TABLE + " (SELLER_ID, SALES_DATE, " +
            "ITEM, TOTAL_QUANTITY) VALUES (?,?,?,?)";
}
