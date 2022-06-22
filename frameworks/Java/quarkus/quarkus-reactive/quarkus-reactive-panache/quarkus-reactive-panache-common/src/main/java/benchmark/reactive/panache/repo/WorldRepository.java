package benchmark.reactive.panache.repo;

import benchmark.reactive.panache.model.World;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WorldRepository implements PanacheRepositoryBase<World, Integer> {}
