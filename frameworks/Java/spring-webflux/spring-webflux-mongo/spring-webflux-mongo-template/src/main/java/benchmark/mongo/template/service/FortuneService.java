package benchmark.mongo.template.service;

import benchmark.common.service.FortuneLikeService;
import benchmark.mongo.common.model.Fortune;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FortuneService implements FortuneLikeService<Fortune> {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public FortuneService(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Flux<Fortune> findAll() {
        return reactiveMongoTemplate.findAll(Fortune.class);
    }

    @Override
    public Fortune create(int id, String message) {
        return new Fortune(id, message);
    }
}
