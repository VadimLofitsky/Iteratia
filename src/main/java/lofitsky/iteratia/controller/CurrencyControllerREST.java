package lofitsky.iteratia.controller;

import lofitsky.iteratia.auxiliary.ExchangeHistoryOperation;
import lofitsky.iteratia.auxiliary.ExchangeHistoryStat;
import lofitsky.iteratia.config.Endpoints;
import lofitsky.iteratia.model.Currency;
import lofitsky.iteratia.model.ExchangeHistory;
import lofitsky.iteratia.service.CurrencyService;
import lofitsky.iteratia.service.ExchangeHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(Endpoints.MAPPING_ROOT)
public class CurrencyControllerREST {

    private CurrencyService currencyService;
    private ExchangeHistoryService historyService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(Endpoints.MAPPING_EXCHANGE_OPERATION_SAVE)
    public void saveExchangeOperation(@RequestParam("curr1") String numCodeFrom,
                               @RequestParam("curr2") String numCodeTo,
                               @RequestParam("amount1") String amount,
                               @RequestParam("date") String timeStamp) {

        LocalDateTime date = LocalDateTime.ofInstant(
                Instant.ofEpochSecond(Long.parseLong(timeStamp)),
                ZoneId.systemDefault());

        Currency from = currencyService.findByNumCode(Integer.parseInt(numCodeFrom));
        Currency to = currencyService.findByNumCode(Integer.parseInt(numCodeTo));

        float relRate = from.getValue() * to.getNominal() / (to.getValue() * from.getNominal());

        ExchangeHistory operation = new ExchangeHistory();
        operation.setDate(date);
        operation.setCurrencyFrom(from);
        operation.setCurrencyTo(to);
        operation.setRate(relRate);
        operation.setAmount(Float.parseFloat(amount));

        historyService.save(operation);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = Endpoints.MAPPING_HISTORY_GET_TOP,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExchangeHistoryOperation> historyGetTop(@RequestParam("lastId") long lastId) {
        return historyService.findAllOps(lastId);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = Endpoints.MAPPING_EXCHANGE_GET_ALL_CURRENCIES,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Currency> allCurrencies(@RequestParam boolean force) {
        return currencyService.findAll(force);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = Endpoints.MAPPING_STATS_GET_TOP,
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ExchangeHistoryStat> allStatRecords() {
        return historyService.weekStat();
    }
}