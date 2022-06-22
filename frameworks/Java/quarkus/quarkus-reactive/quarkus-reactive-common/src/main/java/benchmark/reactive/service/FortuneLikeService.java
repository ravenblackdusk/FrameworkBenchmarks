package benchmark.reactive.service;

import benchmark.model.FortuneLike;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface FortuneLikeService {
    Uni<? extends List<? extends FortuneLike>> findAllFortunes();

    FortuneLike create(int id, String message);
}
