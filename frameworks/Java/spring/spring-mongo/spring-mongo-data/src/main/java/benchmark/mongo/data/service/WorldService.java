package benchmark.mongo.data.service;

import benchmark.mongo.common.model.World;
import benchmark.mongo.data.repo.WorldRepository;
import benchmark.service.WorldLikeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorldService implements WorldLikeService<World> {
    private final WorldRepository worldRepository;

    public WorldService(WorldRepository worldRepository) {this.worldRepository = worldRepository;}

    @Override
    public World findById(int id) {
        return worldRepository.findById(id).orElseThrow();
    }

    @Override
    public List<World> update(List<World> worlds) {
        return worldRepository.saveAll(worlds);
    }
}
