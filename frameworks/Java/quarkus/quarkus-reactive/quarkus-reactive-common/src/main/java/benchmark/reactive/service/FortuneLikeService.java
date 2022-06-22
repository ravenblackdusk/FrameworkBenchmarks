package benchmark.reactive.service;

import benchmark.model.Fortune;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface FortuneLikeService {
    Uni<List<Fortune>> findAllFortunes();

    Fortune create(int id, String message);
}
