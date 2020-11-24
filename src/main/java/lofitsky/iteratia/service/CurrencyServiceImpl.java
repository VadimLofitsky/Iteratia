package lofitsky.iteratia.service;

import lofitsky.iteratia.model.Currency;
import lofitsky.iteratia.model.Update;
import lofitsky.iteratia.repository.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private CurrencyRepo currencyRepo;
    private CbrService cbrService;
    private UpdateService updateService;

    private Map<Integer, String> charCodes;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepo currencyRepo,
                               CbrService cbrService,
                               UpdateService updateService) {

        this.currencyRepo = currencyRepo;
        this.cbrService = cbrService;
        this.updateService = updateService;

        checkUpdates();
    }

    private boolean checkUpdates() {
        Date latestUpdate = updateService.findLatest();
        LocalDate yesterday = LocalDate.now().minusDays(1);

        LocalDate latestUpdateLocal = LocalDate.parse(latestUpdate.toString());
        if ((latestUpdate == null) || yesterday.compareTo(latestUpdateLocal) >= 1) {
            System.out.println("Getting updates");
            charCodes = updateCurrencies();
            System.out.println("Done");
            return true;
        } else {
            System.out.println("All data is up to date");
            return false;
        }
    }

    @Override
    public List<Currency> findAll() {
        return currencyRepo.findAll();
    }

    @Override
    public void save(Currency currency) {
        Currency fromDb = currencyRepo.findByNumCode(currency.getNumCode());

        if (fromDb != null) {
            fromDb.setNominal(currency.getNominal());
            fromDb.setValue(currency.getValue());
            currencyRepo.save(fromDb);
        } else {
            currencyRepo.save(currency);
        }
    }

    @Override
    public void saveAll(List<Currency> list) {
        list.forEach(this::save);
    }

    @Override
    public Map<Integer, String> updateCurrencies() {
        List<Currency> currencies = cbrService.getCurrencies(false);
        saveAll(currencies);

        Update update = new Update();
        update.setDate(cbrService.getDate());
        updateService.save(update);

        Map<Integer, String> charCodes = currencies.stream()
                .collect(Collectors.toMap(Currency::getNumCode, Currency::getCharCode));

        return charCodes;
    }

    @Override
    public String charCodeByNumCode(int numCode) {
        return charCodes.get(numCode);
    }

    @Override
    public Currency findByNumCode(int numCode) {
        return currencyRepo.findByNumCode(numCode);
    }

    @Override
    public List<Currency> findAll(boolean forceSending) {
        if (checkUpdates() || forceSending) {
            return currencyRepo.findAll();
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}