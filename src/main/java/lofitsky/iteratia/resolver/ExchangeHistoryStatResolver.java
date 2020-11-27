package lofitsky.iteratia.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lofitsky.iteratia.auxiliary.ExchangeHistoryStat;
import lofitsky.iteratia.model.Currency;
import lofitsky.iteratia.service.CurrencyService;
import org.springframework.stereotype.Component;

/**
 * Filed resolver запроса статистики
 */

@Component
public class ExchangeHistoryStatResolver implements GraphQLResolver<ExchangeHistoryStat> {

    private CurrencyService currencyService;

    public ExchangeHistoryStatResolver(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public Currency getFrom(ExchangeHistoryStat stat) {
        return currencyService.findByNumCode(stat.getCodeFrom());
    }

    public Currency getTo(ExchangeHistoryStat stat) {
        return currencyService.findByNumCode(stat.getCodeTo());
    }

    public float getTotalSum(ExchangeHistoryStat stat) {
        return stat.getSumTotal();
    }

    public float getAvgRate(ExchangeHistoryStat stat) {
        return stat.getAvgRate();
    }
}