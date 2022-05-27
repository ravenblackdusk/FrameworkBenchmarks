package benchmark.common.service;

import benchmark.common.model.FortuneLike;
import reactor.core.publisher.Flux;

public interface FortuneLikeService<F extends FortuneLike> {
    Flux<F> findAll();

    F create(int id, String message);
}
