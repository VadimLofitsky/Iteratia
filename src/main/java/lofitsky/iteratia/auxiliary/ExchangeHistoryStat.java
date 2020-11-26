package lofitsky.iteratia.auxiliary;

import lofitsky.iteratia.repository.ExchangeHistoryRepo;
import lombok.Getter;

import java.math.BigDecimal;

/**
 * Класс, описывающий ответ на запрос по получению статистики по операциям:
 * <pre>
 * <i>codeFrom, codeTo</i> - числовые коды валют
 * <i>avgRate</i> - средний курс пары
 * <i>sumTotal</i> - суммарный объём первой из пары валют
 * </pre>
 * @see ExchangeHistoryRepo#weekStat
 */

@Getter
public class ExchangeHistoryStat {
    private int codeFrom;
    private int codeTo;
    private float avgRate;
    private float sumTotal;

    /**
     * Создаёт объект по значениям, возвращённым запросом к БД.
     * Преобразует значения из типа Object в соответствующие типы данных.
     * @param args поля результата запроса к БД по получению статистики
     * @see ExchangeHistoryRepo#weekStat
     */
    public ExchangeHistoryStat(Object[] args) {
        codeFrom = ((int) args[0]);
        codeTo = ((int) args[1]);
        avgRate = ((BigDecimal) args[2]).floatValue();
        sumTotal = ((BigDecimal) args[3]).floatValue();
    }
}