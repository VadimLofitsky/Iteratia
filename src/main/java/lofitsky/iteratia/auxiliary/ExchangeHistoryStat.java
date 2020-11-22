package lofitsky.iteratia.auxiliary;

import lombok.Data;

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
        avgRate = ((float) args[2]);
        sumTotal = ((float) args[3]);
        charCodeFrom = ((String) args[4]);
        charCodeTo = ((String) args[5]);
    }
}