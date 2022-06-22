package benchmark.reactive.service;

import benchmark.model.World;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface WorldLikeService {
    Uni<World> findFortuneById(int id);

    Uni<List<World>> update(List<Uni<World>> worlds);
}
