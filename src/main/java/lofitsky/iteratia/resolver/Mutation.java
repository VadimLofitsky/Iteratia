package lofitsky.iteratia.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import lofitsky.iteratia.service.CurrencyService;
import lofitsky.iteratia.service.ExchangeHistoryService;
import org.springframework.stereotype.Component;

/**
 * Resolver для Mutation GraphQL
 */

@Component
public class Mutation implements GraphQLMutationResolver {

    private ExchangeHistoryService historyService;

    public Mutation(CurrencyService currencyService, ExchangeHistoryService historyService) {
        this.historyService = historyService;
    }

    public long createExchangeHistoryOperation(String date, String numCodeFrom, String numCodeTo, String amount) {
        return historyService.save(date, numCodeFrom, numCodeTo, amount).getId();
    }
}