package benchmark.r2dbc.repo;

import benchmark.r2dbc.model.Fortune;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface FortuneRepository extends R2dbcRepository<Fortune, Integer> {}
