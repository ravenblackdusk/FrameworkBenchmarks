package benchmark.r2dbc.common.repo;

import benchmark.r2dbc.common.model.Fortune;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface FortuneRepository extends R2dbcRepository<Fortune, Integer> {}
