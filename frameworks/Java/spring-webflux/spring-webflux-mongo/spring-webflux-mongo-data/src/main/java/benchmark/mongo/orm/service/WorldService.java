package benchmark.mongo.orm.service;

import benchmark.common.service.WorldLikeService;
import benchmark.mongo.common.model.World;
import benchmark.mongo.orm.repo.WorldRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
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
