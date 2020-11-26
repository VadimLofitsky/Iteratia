package lofitsky.iteratia.repository;

import lofitsky.iteratia.model.Update;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Репозиторий для {@link Update}.
 */

@Repository
public interface UpdateRepo extends CrudRepository<Update, Long> {

    /**
     * Получает запись с датой последнего обновления курсов валют.
     * @return {@link Update}
     */
    Update findFirstByOrderByDateDesc();
}