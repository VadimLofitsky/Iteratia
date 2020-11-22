package lofitsky.iteratia.repository;

import lofitsky.iteratia.model.ExchangeHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExchangeHistoryRepo extends JpaRepository<ExchangeHistory, Long> {

    <T extends ExchangeHistory> T save(T record);

    List<ExchangeHistory> findAll();

    @Query(nativeQuery = true, value = "select distinct on (currency_from_num_code, currency_to_num_code)" +
            "        currency_from_num_code code_from, " +
            "        currency_to_num_code code_to, " +
            "        avg(rate) avg_rate, " +
            "        sum(amount) sum_total " +
            "    from exchange_history " +
            "    where date >= (now() - interval '1 week') " +
            "    group by currency_from_num_code, currency_to_num_code")
    List<Object[]> weekStat();
}