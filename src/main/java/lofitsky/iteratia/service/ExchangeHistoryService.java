package lofitsky.iteratia.service;

import lofitsky.iteratia.auxiliary.ExchangeHistoryOperation;
import lofitsky.iteratia.auxiliary.ExchangeHistoryStat;
import lofitsky.iteratia.model.ExchangeHistory;
import lofitsky.iteratia.repository.ExchangeHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeHistoryService {

    private ExchangeHistoryRepo historyRepo;

    @Autowired
    public ExchangeHistoryService(ExchangeHistoryRepo historyRepo) {
        this.historyRepo = historyRepo;
    }

    public void save(ExchangeHistory record) {
        historyRepo.save(record);
    }

    public List<ExchangeHistory> findAll() {
        return historyRepo.findAll();
    }

    public List<ExchangeHistoryStat> weekStat() {
        List<ExchangeHistoryStat> stats = historyRepo.weekStat().stream()
                .map(ExchangeHistoryStat::new)
                .collect(Collectors.toList());

        return stats;
    }

    public List<ExchangeHistoryOperation> findAllOps(long lastId) {
        return historyRepo.findAllByIdGreaterThanOrderByIdDesc(lastId)
                            .stream()
                            .map(op -> ExchangeHistoryOperation.builder()
                            .id(op.getId())
                            .date(op.getDate().toString())
                            .charCode1(op.getCurrencyFrom().getCharCode())
                            .numCode1(op.getCurrencyFrom().getNumCode())
                            .name1(op.getCurrencyFrom().getName())
                            .amount1(op.getAmount())
                            .charCode2(op.getCurrencyTo().getCharCode())
                            .numCode2(op.getCurrencyTo().getNumCode())
                            .name2(op.getCurrencyTo().getName())
                            .amount2(op.getAmount() * op.getRate())
                            .rate(op.getRate())
                            .build())
                .collect(Collectors.toList());
    }
}