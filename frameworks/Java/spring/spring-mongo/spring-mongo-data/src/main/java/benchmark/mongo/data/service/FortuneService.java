package benchmark.mongo.data.service;

import benchmark.mongo.common.model.Fortune;
import benchmark.mongo.data.repo.FortuneRepository;
import benchmark.service.FortuneLikeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FortuneService implements FortuneLikeService<Fortune> {
    private final FortuneRepository fortuneRepository;

    public FortuneService(FortuneRepository fortuneRepository) {this.fortuneRepository = fortuneRepository;}

    @Override
    public List<Fortune> findAll() {
        return fortuneRepository.findAll();
    }

    @Override
    public Fortune create(int id, String message) {
        return new Fortune(id, message);
    }
}
