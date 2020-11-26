package lofitsky.iteratia.service;

import lofitsky.iteratia.model.Currency;
import lofitsky.iteratia.model.Update;
import lofitsky.iteratia.repository.CurrencyRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис работы с таблицей валют.
 */

@Service
public class CurrencyService {

    private CurrencyRepo currencyRepo;
    private CbrService cbrService;
    private UpdateService updateService;

    public CurrencyService(CurrencyRepo currencyRepo,
                           CbrService cbrService,
                           UpdateService updateService) {

        this.currencyRepo = currencyRepo;
        this.cbrService = cbrService;
        this.updateService = updateService;
    }

    /**
     * Сравнивает текущую дату и дату последнего обновления данных о курсах, сохранённую в БД.
     * В случае, если последнее обновление производилось более одного дня назад,
     * делает запрос к источнику на получение актуальных данных и сохраняет их в БД.
     * @return <i>true</i>, если обновления данных получены, и <i>false</i> в противном случае
     * @see UpdateService#expired
     */
    private boolean checkUpdates() {
        if (updateService.expired(LocalDate.now())) {
            System.out.println("Getting updates");
            updateCurrencies();
            System.out.println("Done");
            return true;
        } else {
            System.out.println("All data is up to date");
            return false;
        }
    }

    /**
     * Возвращает список всех валют из таблицы.
     * @return список валют
     */
    public List<Currency> findAll() {
        return (List<Currency>) currencyRepo.findAll();
    }

    private void saveAll(List<Currency> list) {
        currencyRepo.saveAll(list);
    }

    /**
     * Получает из источника актуальные данные о курсах валют,
     * обновляет данные о существующих в БД валютах и сохраняет новые
     * @see CbrService
     */
    private void updateCurrencies() {
        List<Currency> currencies = cbrService.getCurrencies();
        currencies = currencies.stream()
                .map(currency -> {
                    Currency fromDb = currencyRepo.findByNumCode(currency.getNumCode());

                    if (fromDb != null) {
                        currency.setId(fromDb.getId());
                    }

                    return currency;
                })
                .collect(Collectors.toList());

        saveAll(currencies);

        Update update = new Update();
        update.setDate(cbrService.getDate());
        updateService.save(update);
    }

    /**
     * Возвращает из БД валюту по её числовому коду.
     * @param numCode числовой код валюты
     * @return {@link Currency}
     */
    public Currency findByNumCode(int numCode) {
        return currencyRepo.findByNumCode(numCode);
    }

    /**
     * Проверяет обновления и возвращает список валют.<br>
     * Из БД список возвращается в случае наличия обновлений или если установлен флаг <i>forceSending</i>.<br>
     * В противном случает возвращает пустой список.
     * @param forceSending форсировать или нет возвращение списка
     * @return список валют
     * @see #checkUpdates()
     */
    public List<Currency> findAll(boolean forceSending) {
        if (checkUpdates() || forceSending) {
            return findAll();
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * Вычисление курса между валютами на основании их курсов к рублю.
     * @param from валюта, из которой производится конвертация
     * @param to валюта, в которую производится конвертация
     * @return относительный курс валют
     */
    public float relativeRate(Currency from, Currency to) {
        return from.getValue() * to.getNominal() / (to.getValue() * from.getNominal());
    }
}