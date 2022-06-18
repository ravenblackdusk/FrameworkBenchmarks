package io.quarkus.benchmark.repository;

import io.quarkus.benchmark.model.World;
import io.quarkus.benchmark.utils.LocalRandom;
import io.quarkus.benchmark.utils.Randomizer;
import io.smallrye.mutiny.Uni;
import org.hibernate.reactive.mutiny.Mutiny;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class WorldRepository extends BaseRepository {
    public Uni<List<World>> update(Mutiny.Session session, List<World> worlds) {
        return session
                .setBatchSize(worlds.size())
                .flush()
                .map(v -> worlds);
    }

    public Uni<List<World>> findStateless(int count) {
        return inStatelessSession(session -> findStateless(session, count));
    }

    private Uni<List<World>> findStateless(Mutiny.StatelessSession s, int count) {
        //The rules require individual load: we can't use the Hibernate feature which allows load by multiple IDs
        // as one single operation as Hibernate is too smart and will switch to use batched loads automatically.
        // Hence, use this awkward alternative:
        final LocalRandom localRandom = Randomizer.current();
        List<Uni<World>> l = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            l.add(s.get(World.class, localRandom.getNextRandom()));
        }
        return Uni.join().all(l).andFailFast();
    }

    public Uni<World> findStateless() {
        return inStatelessSession(session -> session.get(World.class, Randomizer.current().getNextRandom()));
    }
}
