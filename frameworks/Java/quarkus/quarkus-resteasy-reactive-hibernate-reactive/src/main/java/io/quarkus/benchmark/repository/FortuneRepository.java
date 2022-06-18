package io.quarkus.benchmark.repository;

import io.quarkus.benchmark.model.Fortune;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class FortuneRepository extends BaseRepository {
    public Uni<List<Fortune>> findAll() {
        return inStatelessSession(
                session -> session.createQuery("SELECT F FROM Fortune F", Fortune.class).getResultList());
    }

}
