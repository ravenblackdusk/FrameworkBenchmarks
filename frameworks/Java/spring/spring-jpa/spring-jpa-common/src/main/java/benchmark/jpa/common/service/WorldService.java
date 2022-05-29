package benchmark.jpa.common.service;

import benchmark.jpa.common.model.World;
import benchmark.jpa.common.repo.WorldRepository;
import benchmark.service.WorldLikeService;

import java.util.List;

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
