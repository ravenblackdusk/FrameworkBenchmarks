package benchmark.reactive.panache.service;

import benchmark.model.World;
import benchmark.reactive.service.WorldLikeService;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.tuples.Tuple2;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class WorldService implements PanacheRepository<World>, WorldLikeService {
    @Override
    public Uni<World> findFortuneById(int id) {
        return findById((long) id);
    }

    @Override
    public Uni<List<World>> update(List<Uni<World>> worlds) {
        return Uni.join().all(worlds.stream().map(world -> world.flatMap(it -> Uni.combine().all()
                .unis(update("randomnumber = ?1 where id = ?2", it.getRandomnumber(), it.getId()), world).asTuple()
                .map(Tuple2::getItem2))).collect(toList())).andFailFast();
    }
}
