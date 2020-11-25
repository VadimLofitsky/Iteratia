package lofitsky.iteratia.auxiliary;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExchangeHistoryOperation {
    private long id;
    private String date;
    private String charCode1;
    private int numCode1;
    private String name1;
    private float amount1;
    private String charCode2;
    private int numCode2;
    private String name2;
    private float amount2;
    private float rate;
}