package benchmark.reactive.panache.service;

import benchmark.model.WorldLike;
import benchmark.reactive.panache.repo.WorldRepository;
import benchmark.reactive.service.WorldLikeService;
import io.quarkus.hibernate.reactive.panache.Panache;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class WorldService implements WorldLikeService {
    @Inject
    WorldRepository worldRepository;

    @Override
    public Uni<WorldLike> findFortuneById(int id) {
        return worldRepository.findById(id).map(it -> it);
    }

    @Override
    public Uni<List<WorldLike>> update(List<Uni<WorldLike>> worlds) {
        return Panache.withTransaction(() -> Uni.join().all(worlds.stream().map(worldUni -> worldUni.flatMap(
                world -> worldRepository.update("randomnumber = ?1 where id = ?2", world.getRandomnumber(),
                        world.getId()).flatMap(it -> worldUni))).collect(toList())).andFailFast());
    }
}
