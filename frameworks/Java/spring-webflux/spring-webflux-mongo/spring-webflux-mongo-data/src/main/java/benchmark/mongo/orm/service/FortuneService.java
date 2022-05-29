package benchmark.mongo.orm.service;

import benchmark.common.service.FortuneLikeService;
import benchmark.mongo.common.model.Fortune;
import benchmark.mongo.orm.repo.FortuneRepository;
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
