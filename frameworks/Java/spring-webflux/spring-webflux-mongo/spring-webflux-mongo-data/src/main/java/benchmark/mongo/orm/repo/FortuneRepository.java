package benchmark.mongo.orm.repo;

import benchmark.mongo.common.model.Fortune;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface FortuneRepository extends ReactiveMongoRepository<Fortune, Integer> {}
