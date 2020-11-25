package lofitsky.iteratia.auxiliary;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeHistoryStat {
    private int codeFrom;
    private int codeTo;
    private float avgRate;
    private float sumTotal;
    private String charCodeFrom;
    private String charCodeTo;

    public ExchangeHistoryStat(Object[] args) {
        codeFrom = ((int) args[0]);
        codeTo = ((int) args[1]);
        avgRate = ((BigDecimal) args[2]).floatValue();
        sumTotal = ((BigDecimal) args[3]).floatValue();
    }
}