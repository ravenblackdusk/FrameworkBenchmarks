package benchmark.reactive.panache.repo;

import benchmark.reactive.panache.model.Fortune;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FortuneRepository implements PanacheRepository<Fortune> {}
