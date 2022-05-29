package benchmark.mongo.data.repo;

import benchmark.mongo.common.model.World;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorldRepository extends MongoRepository<World, Integer> {}
