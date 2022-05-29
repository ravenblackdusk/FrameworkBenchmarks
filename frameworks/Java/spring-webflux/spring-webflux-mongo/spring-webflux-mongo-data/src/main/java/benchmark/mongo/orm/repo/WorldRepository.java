package benchmark.mongo.orm.repo;

import benchmark.mongo.common.model.World;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface WorldRepository extends ReactiveMongoRepository<World, Integer> {}
