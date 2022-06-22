package benchmark.blocking.service;

import benchmark.model.World;

import java.util.List;

public interface WorldLikeService {
    World findFortuneById(int id);

    List<World> update(List<World> worlds);
}
