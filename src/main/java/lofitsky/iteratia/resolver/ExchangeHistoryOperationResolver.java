package lofitsky.iteratia.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import lofitsky.iteratia.auxiliary.ExchangeHistoryOperation;
import lofitsky.iteratia.model.Currency;
import lofitsky.iteratia.service.CurrencyService;
import org.springframework.stereotype.Component;

/**
 * Filed resolver запроса истории операций
 */

@Component
public class ExchangeHistoryOperationResolver implements GraphQLResolver<ExchangeHistoryOperation> {

    private CurrencyService currencyService;

    public ExchangeHistoryOperationResolver(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    public Currency getFrom(ExchangeHistoryOperation operation) {
        return currencyService.findByNumCode(operation.getNumCode1());
    }

    public Currency getTo(ExchangeHistoryOperation operation) {
        return currencyService.findByNumCode(operation.getNumCode2());
    }

    public float getAmountFrom(ExchangeHistoryOperation operation) {
        return operation.getAmount1();
    }

    public float getAmountTo(ExchangeHistoryOperation operation) {
        return operation.getAmount2();
    }
}