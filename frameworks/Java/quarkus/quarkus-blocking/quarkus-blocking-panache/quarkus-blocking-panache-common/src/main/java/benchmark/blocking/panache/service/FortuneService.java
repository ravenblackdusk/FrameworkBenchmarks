package benchmark.blocking.panache.service;

import benchmark.blocking.panache.model.Fortune;
import benchmark.blocking.service.FortuneLikeService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FortuneService implements PanacheRepository<Fortune>, FortuneLikeService {
    @Override
    public List<Fortune> findAllFortunes() {
        return findAll().list();
    }

    @Override
    public Fortune create(int id, String message) {
        return new Fortune(id, message);
    }
}
