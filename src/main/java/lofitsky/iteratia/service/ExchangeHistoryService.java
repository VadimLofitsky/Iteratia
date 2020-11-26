package lofitsky.iteratia.service;

import lofitsky.iteratia.auxiliary.ExchangeHistoryOperation;
import lofitsky.iteratia.auxiliary.ExchangeHistoryStat;
import lofitsky.iteratia.model.ExchangeHistory;
import lofitsky.iteratia.repository.ExchangeHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис работы с таблицей истории операций.
 */

@Service
public class ExchangeHistoryService {

    private ExchangeHistoryRepo historyRepo;

    @Autowired
    public ExchangeHistoryService(ExchangeHistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    public void save(ExchangeHistory record) {
        historyRepo.save(record);
    }

    /**
     * Возвращает данные статистики по операциям за последнюю неделю.
     * Список предназначен для преобразования в JSON-объект
     * для передачи в качестве ответа REST-контроллером {@link lofitsky.iteratia.controller.CurrencyControllerREST}.
     * @return список {@link ExchangeHistoryStat}
     * @see ExchangeHistoryRepo#weekStat
     */
    public List<ExchangeHistoryStat> weekStat() {
        List<ExchangeHistoryStat> stats = historyRepo.weekStat().stream()
                .map(ExchangeHistoryStat::new)
                .collect(Collectors.toList());

        return stats;
    }

    /**
     * Возвращает список операций с id, большим указанного. Список отсортирован по убыванию id
     * и предназначен для преобразования в JSON-объект
     * для передачи в качестве ответа REST-контроллером {@link lofitsky.iteratia.controller.CurrencyControllerREST}.
     * @param lastId id для фильтрации записей (наибольший из непопадающих в выборку)
     * @return отсортированный по убыванию id список операций (список {@link ExchangeHistoryOperation})
     */
    public List<ExchangeHistoryOperation> findAllOps(long lastId) {
        return historyRepo.findAllByIdGreaterThanOrderByIdDesc(lastId)
                .stream()
                // преобразование в объекты ExchangeHistoryOperation
                .map(op -> ExchangeHistoryOperation.builder()
                    .id(op.getId())
                    .date(op.getDate().toString())
                    .charCode1(op.getCurrencyFrom().getCharCode())
                    .numCode1(op.getCurrencyFrom().getNumCode())
                    .name1(op.getCurrencyFrom().getName())
                    .amount1(op.getAmount())
                    .charCode2(op.getCurrencyTo().getCharCode())
                    .numCode2(op.getCurrencyTo().getNumCode())
                    .name2(op.getCurrencyTo().getName())
                    .amount2(op.getAmount() * op.getRate())
                    .rate(op.getRate())
                .build())
                .collect(Collectors.toList());
    }
}