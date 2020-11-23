package lofitsky.iteratia.service;

import lofitsky.iteratia.model.Currency;

import java.util.List;
import java.util.Map;

public interface CurrencyService {
    List<Currency> findAll();

    void save(Currency currency);

    void saveAll(List<Currency> list);

    Map<Integer, String> updateCurrencies();

    String charCodeByNumCode(int numCode);

    Currency findByNumCode(int numCode);
}