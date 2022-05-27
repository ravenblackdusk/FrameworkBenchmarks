package benchmark.r2dbc.repo;

import benchmark.r2dbc.model.World;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface WorldRepository extends R2dbcRepository<World, Integer> {}
