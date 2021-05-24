package com.jmb.batchapp.parameter;

/**
 * Class holds parameter names, valid ones, for {@link com.jmb.batchapp.job.implementation.SalesSummaryJob}
 *
 * @author JuanMBruno.
 */
public enum SalesSummaryJobParams implements Parameter<SalesSummaryJobParams> {

    SELLER_ID("sellerId"),
    DATE("date"),
    PRODUCT("product");

    private String paramName;

    SalesSummaryJobParams(String paramName) {
        this.paramName = paramName;
    }

    @Override
    public String getParamName() {
        return paramName;
    }
}
