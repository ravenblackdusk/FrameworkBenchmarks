package benchmark.r2dbc.service;

import benchmark.common.service.FortuneLikeService;
import benchmark.r2dbc.model.Fortune;
import benchmark.r2dbc.repo.FortuneRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FortuneService implements FortuneLikeService<Fortune> {
    private final FortuneRepository fortuneRepository;

    public FortuneService(FortuneRepository fortuneRepository) {this.fortuneRepository = fortuneRepository;}

    @Override
    public Flux<Fortune> findAll() {
        return fortuneRepository.findAll();
    }

    @Override
    public Fortune create(int id, String message) {
        return new Fortune(id, message);
    }
}
