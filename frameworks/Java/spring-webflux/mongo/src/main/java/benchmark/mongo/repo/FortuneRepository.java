package benchmark.mongo.repo;

import benchmark.mongo.model.Fortune;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FortuneRepository extends ReactiveMongoRepository<Fortune, Integer> {}
