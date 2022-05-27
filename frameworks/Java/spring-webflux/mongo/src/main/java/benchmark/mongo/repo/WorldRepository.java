package benchmark.mongo.repo;

import benchmark.mongo.model.World;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface WorldRepository extends ReactiveMongoRepository<World, Integer> {}
