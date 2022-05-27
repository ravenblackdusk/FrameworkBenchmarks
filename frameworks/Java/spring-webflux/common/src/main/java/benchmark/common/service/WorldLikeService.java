package benchmark.common.service;

import benchmark.common.model.WorldLike;
import reactor.core.publisher.Mono;

public interface WorldLikeService<W extends WorldLike> {
    Mono<W> findById(int id);

    Mono<W> save(W world);
}
