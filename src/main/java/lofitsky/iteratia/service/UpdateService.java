package lofitsky.iteratia.service;

import lofitsky.iteratia.model.Update;
import lofitsky.iteratia.repository.UpdateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

/**
 * Сервис по обращению к таблице обновлений курсов.
 */

@Service
public class UpdateService {

    private UpdateRepo updateRepo;

    @Autowired
    public UpdateService(UpdateRepo updateRepo) {
        this.updateRepo = updateRepo;
    }

    /**
     * Возвращает последнюю дату полученных обновлений.
     * @return java.util.Date
     */
    public Date findLatest() {
        Update latest = updateRepo.findFirstByOrderByDateDesc();
        return latest != null ? latest.getDate() : null;
    }

    void save(Update u) {
        updateRepo.save(u);
    }

    /**
     * Сравнивает заданную дату с датой получения последнего обновления.
     * @param date сравниваемая дата
     * @return <i>true</i>, если сохранённая дата меньше заданной на день или более,
     * иначе <i>false</i>
     */
    boolean expired(LocalDate date) {
        Date latestUpdate = findLatest();
        LocalDate dayBefore = date.minusDays(1);

        return (latestUpdate == null) ||
               dayBefore.compareTo(LocalDate.parse(latestUpdate.toString())) >= 1;
    }
}