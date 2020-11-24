package lofitsky.iteratia.service;

import lofitsky.iteratia.auxiliary.ExchangeHistoryOperation;
import lofitsky.iteratia.auxiliary.ExchangeHistoryStat;
import lofitsky.iteratia.model.ExchangeHistory;

import java.util.List;

public interface ExchangeHistoryService {
    void save(ExchangeHistory record);

    List<ExchangeHistory> findAll();

    List<ExchangeHistoryStat> weekStat();

    List<ExchangeHistoryOperation> findAllOps(long lastId);
}