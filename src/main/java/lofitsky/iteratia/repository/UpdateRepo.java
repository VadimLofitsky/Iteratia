package lofitsky.iteratia.repository;

import lofitsky.iteratia.model.Update;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UpdateRepo extends CrudRepository<Update, Long> {
    Update findFirstByOrderByDateDesc();
}