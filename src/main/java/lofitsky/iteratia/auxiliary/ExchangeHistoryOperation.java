package lofitsky.iteratia.auxiliary;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс, описывающий ответ на запрос по получению операции конвертирования валют:
 * <pre>
 * <i>id</i> - идентификатор в таблице базы данных
 * <i>date</i> - да совершения операции
 * <i>charCode1, charCode2</i> - символьные коды валют
 * <i>numCode1, numCode2</i> - числовые коды валют
 * <i>name1, name2</i> - названия валют
 * <i>amount1, amount2</i> - соответствующие валютам объёмы операции
 * <i>rate</i> - курс конвертации
 * </pre>
 */

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