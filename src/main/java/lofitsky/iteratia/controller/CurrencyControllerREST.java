package lofitsky.iteratia.controller;

import lofitsky.iteratia.config.Endpoints;
import lofitsky.iteratia.model.Currency;
import lofitsky.iteratia.model.ExchangeHistory;
import lofitsky.iteratia.service.CurrencyService;
import lofitsky.iteratia.service.ExchangeHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@RestController
public class CurrencyControllerREST {

    private CurrencyService currencyService;
    private ExchangeHistoryService historyService;

    @Autowired
    public CurrencyControllerREST(CurrencyService currencyService, ExchangeHistoryService historyService) {
        this.currencyService = currencyService;
        this.historyService = historyService;
    }

    @PostMapping(Endpoints.MAPPING_EXCHANGE_OPERATION_SAVE)
    void saveExchangeOperation(@RequestParam("curr1") String numCodeFrom,
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
}