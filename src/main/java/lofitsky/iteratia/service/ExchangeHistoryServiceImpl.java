package lofitsky.iteratia.service;

import lofitsky.iteratia.auxiliary.ExchangeHistoryStat;
import lofitsky.iteratia.model.ExchangeHistory;
import lofitsky.iteratia.repository.ExchangeHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeHistoryServiceImpl implements ExchangeHistoryService {

    private ExchangeHistoryRepo historyRepo;
    private CurrencyService currencyService;

    @Autowired
    public ExchangeHistoryServiceImpl(ExchangeHistoryRepo historyRepo, CurrencyService currencyService) {
        this.historyRepo = historyRepo;
        this.currencyService = currencyService;
    }

    @Override
    public void save(ExchangeHistory record) {
        historyRepo.save(record);
    }

    @Override
    public List<ExchangeHistory> findAll() {
        return historyRepo.findAll();
    }

    @Override
    public List<ExchangeHistoryStat> weekStat() {
        List<ExchangeHistoryStat> stats = historyRepo.weekStat().stream()
                .map(ExchangeHistoryStat::new)
                .collect(Collectors.toList());

        stats.forEach(stat ->
                stat.setCharCodeFrom(currencyService.charCodeByNumCode(stat.getCodeFrom())));

        stats.forEach(stat ->
                stat.setCharCodeTo(currencyService.charCodeByNumCode(stat.getCodeTo())));

        return stats;
    }
}