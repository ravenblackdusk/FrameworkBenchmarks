package benchmark.r2dbc.common.service;

import benchmark.common.service.WorldLikeService;
import benchmark.r2dbc.common.model.World;
import benchmark.r2dbc.common.repo.WorldRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WorldService implements WorldLikeService<World> {
    private final WorldRepository worldRepository;

    public WorldService(WorldRepository worldRepository) {this.worldRepository = worldRepository;}

    @Override
    public Mono<World> findById(int id) {
        return worldRepository.findById(id);
    }

    @Override
    public Flux<World> update(Flux<World> worlds) {
        return worldRepository.saveAll(worlds);
    }
}
