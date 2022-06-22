package benchmark.blocking.panache.service;

import benchmark.blocking.panache.model.World;
import benchmark.blocking.service.WorldLikeService;
import benchmark.model.WorldLike;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@ApplicationScoped
public class WorldService implements PanacheRepositoryBase<World, Integer>, WorldLikeService {
    @Override
    public World findFortuneById(int id) {
        return findById(id);
    }

    @Override
    public List<WorldLike> update(List<WorldLike> worlds) {
        worlds.forEach(world -> update("randomnumber = ?1 where id = ?2", world.getRandomnumber(), world.getId()));
        return worlds;
    }
}
