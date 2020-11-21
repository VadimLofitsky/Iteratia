package lofitsky.iteratia.service;

import lofitsky.iteratia.model.Currency;

import java.util.List;

public interface CurrencyService {
    List<Currency> findAll();

    void save(Currency currency);

    void saveAll(List<Currency> list);

    void updateCurrencies();
}