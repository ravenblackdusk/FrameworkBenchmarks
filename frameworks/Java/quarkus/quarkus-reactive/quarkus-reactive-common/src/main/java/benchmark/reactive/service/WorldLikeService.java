package benchmark.reactive.service;

import benchmark.model.WorldLike;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface WorldLikeService {
    Uni<WorldLike> findFortuneById(int id);

    Uni<List<WorldLike>> update(List<Uni<WorldLike>> worlds);
}
