package lofitsky.iteratia.repository;

import lofitsky.iteratia.model.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepo extends CrudRepository<Currency, Long> {

    List<Currency> findAll();

    Currency findCurrencyById(long id);

    Currency save(Currency currency);

    <S extends Currency> Iterable<S> saveAll(Iterable<S> list);

    long deleteById(long id);
}