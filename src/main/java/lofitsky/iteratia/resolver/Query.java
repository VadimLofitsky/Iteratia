package lofitsky.iteratia.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lofitsky.iteratia.auxiliary.ExchangeHistoryOperation;
import lofitsky.iteratia.auxiliary.ExchangeHistoryStat;
import lofitsky.iteratia.model.Currency;
import lofitsky.iteratia.service.CurrencyService;
import lofitsky.iteratia.service.ExchangeHistoryService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Resolver для root Query GraphQL
 */

@Component
public class Query implements GraphQLQueryResolver {

    private CurrencyService currencyService;
    private ExchangeHistoryService historyService;

    public Query(CurrencyService currencyService, ExchangeHistoryService historyService) {
        this.currencyService = currencyService;
        this.historyService = historyService;
    }

    public List<Currency> getAllCurrencies() {
        return currencyService.findAll();
    }

    public List<ExchangeHistoryOperation> historyRecords(int afterId) {
        return historyService.findAllOps(afterId);
    }

    public List<ExchangeHistoryStat> allStats() {
        return historyService.weekStat();
    }
}