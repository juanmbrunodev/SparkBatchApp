package com.jmb.batchapp.dictionary;

public class SalesSummaryQueries {

    public static String SALES_TABLE = "SALES";

    public static String STRING_TOKEN = "%s";

    public static String SALES_PER_SELLER_ID_DATE_PROD = "SELECT SELLER_ID, SALES_DATE, PRODUCT, QUANTITY FROM "
            + SALES_TABLE + " WHERE " + " SELLER_ID IN (" + STRING_TOKEN + ") AND SALES_DATE = PARSEDATETIME('"
            + STRING_TOKEN + "','yyyyMMddHHmmss') " + " AND PRODUCT = '" + STRING_TOKEN + "' ";
}
