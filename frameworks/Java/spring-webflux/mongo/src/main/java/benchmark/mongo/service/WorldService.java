package benchmark.mongo.service;

import benchmark.common.service.WorldLikeService;
import benchmark.mongo.model.World;
import benchmark.mongo.repo.WorldRepository;
import org.springframework.stereotype.Service;
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
    public Mono<World> save(World world) {
        return worldRepository.save(world);
    }
}
