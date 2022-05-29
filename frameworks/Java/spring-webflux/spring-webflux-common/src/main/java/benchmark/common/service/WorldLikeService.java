package benchmark.common.service;

import benchmark.common.model.WorldLike;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WorldLikeService<W extends WorldLike> {
    Mono<W> findById(int id);

    Flux<W> update(Flux<W> worlds);
}
