package benchmark.reactive.panache.service;

import benchmark.model.Fortune;
import benchmark.reactive.service.FortuneLikeService;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FortuneService implements PanacheRepository<Fortune>, FortuneLikeService {
    @Override
    public Uni<List<Fortune>> findAllFortunes() {
        return findAll().list();
    }

    @Override
    public Fortune create(int id, String message) {
        return new Fortune(id, message);
    }
}
