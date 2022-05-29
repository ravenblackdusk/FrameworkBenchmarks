package benchmark.jpa.common.service;

import benchmark.jpa.common.model.Fortune;
import benchmark.jpa.common.repo.FortuneRepository;
import benchmark.service.FortuneLikeService;

import java.util.List;

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
