package benchmark.reactive.panache.service;

import benchmark.reactive.panache.model.Fortune;
import benchmark.reactive.panache.repo.FortuneRepository;
import benchmark.reactive.service.FortuneLikeService;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class FortuneService implements FortuneLikeService {
    @Inject
    FortuneRepository fortuneRepository;

    @Override
    public Uni<List<Fortune>> findAllFortunes() {
        return fortuneRepository.findAll().list();
    }

    @Override
    public Fortune create(int id, String message) {
        return new Fortune(id, message);
    }
}
