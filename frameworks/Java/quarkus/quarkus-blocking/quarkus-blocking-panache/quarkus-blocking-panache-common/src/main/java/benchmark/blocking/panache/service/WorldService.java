package benchmark.blocking.panache.service;

import benchmark.blocking.panache.model.World;
import benchmark.blocking.service.WorldLikeService;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class WorldService implements PanacheRepository<World>, WorldLikeService {
    @Override
    public World findFortuneById(int id) {
        return findById((long) id);
    }

    @Override
    public List<benchmark.model.World> update(List<benchmark.model.World> worlds) {
        worlds.forEach(world -> update("randomnumber = ?1 where id = ?2", world.getRandomnumber(), world.getId()));
        return worlds;
    }
}
