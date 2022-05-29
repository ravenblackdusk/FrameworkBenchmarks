package benchmark.jpa.common.repo;

import benchmark.jpa.common.model.Fortune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FortuneRepository extends JpaRepository<Fortune, Integer> {}
