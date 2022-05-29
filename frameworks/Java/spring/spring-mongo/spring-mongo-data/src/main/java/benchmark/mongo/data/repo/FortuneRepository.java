package benchmark.mongo.data.repo;

import benchmark.mongo.common.model.Fortune;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FortuneRepository extends MongoRepository<Fortune, Integer> {}
