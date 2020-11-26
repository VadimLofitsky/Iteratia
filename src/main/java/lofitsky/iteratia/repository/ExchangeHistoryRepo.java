package lofitsky.iteratia.repository;

import lofitsky.iteratia.model.ExchangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с историей операций.
 */

@Repository
public interface ExchangeHistoryRepo extends JpaRepository<ExchangeHistory, Long> {

    <T extends ExchangeHistory> T save(T record);

    List<ExchangeHistory> findAll();

    /**
     * Получает данные статистики по операциям за неделю.
     * @return массив (типа Object) данных по статистике:<br>
     * [0] - (int) числовой код валюты<br>
     * [1] - (int) числовой код валюты<br>
     * [2] - (BigDecimal) средний курс пары<br>
     * [3] - (BigDecimal) суммарный объём первой валюты пары
     */
    @Query(nativeQuery = true, value = "select distinct on (currency_from_num_code, currency_to_num_code)" +
            "        currency_from_num_code code_from, " +
            "        currency_to_num_code code_to, " +
            "        avg(rate) avg_rate, " +
            "        sum(amount) sum_total " +
            "    from exchange_history " +
            "    where date >= (now() - interval '1 week') " +
            "    group by currency_from_num_code, currency_to_num_code")
    List<Object[]> weekStat();

    /**
     * Получает список записей операций с id, большим указанного. Список отсортирован по убыванию id.
     * @param lastId id для фильтрации записей (наибольший из непопадающих в выборку)
     * @return отсортированный по убыванию id список операций
     */
    List<ExchangeHistory> findAllByIdGreaterThanOrderByIdDesc(long lastId);
}