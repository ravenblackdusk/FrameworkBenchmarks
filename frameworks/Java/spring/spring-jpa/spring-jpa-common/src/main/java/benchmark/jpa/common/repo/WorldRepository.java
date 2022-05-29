package benchmark.jpa.common.repo;

import benchmark.jpa.common.model.World;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorldRepository extends JpaRepository<World, Integer> {}
