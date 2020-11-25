package lofitsky.iteratia.service;

import lofitsky.iteratia.model.Update;
import lofitsky.iteratia.repository.UpdateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UpdateService {

    private UpdateRepo updateRepo;

    @Autowired
    public UpdateService(UpdateRepo updateRepo) {
        this.updateRepo = updateRepo;
    }

    public Date findLatest() {
        Update latest = updateRepo.findFirstByOrderByDateDesc();
        return latest != null ? latest.getDate() : null;
    }

    void save(Update u) {
        updateRepo.save(u);
    }
}