package lofitsky.iteratia.repository;

import lofitsky.iteratia.model.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CurrencyRepo extends CrudRepository<Currency, Long> {

    List<Currency> findAll();

    Currency findByNumCode(int numCode);

    @Transactional
    Currency save(Currency currency);

    @Transactional
    <S extends Currency> Iterable<S> saveAll(Iterable<S> list);

    @Transactional
    void deleteAll();
}