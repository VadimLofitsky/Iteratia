package lofitsky.iteratia.service;

import lofitsky.iteratia.model.Currency;
import lofitsky.iteratia.repository.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepo currencyRepo;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepo currencyRepo) {
        this.currencyRepo = currencyRepo;
    }

    public List<Currency> findAll() {
        return currencyRepo.findAll();
    }

    public Currency findById(long id) {
        return currencyRepo.findCurrencyById(id);
    }

    public void save(Currency currency) {
        currencyRepo.save(currency);
    }

    public void saveAll(List<Currency> list) {
        currencyRepo.saveAll(list);
    }

    @Transactional
    public void delete(long id) {
        currencyRepo.deleteById(id);
    }
}