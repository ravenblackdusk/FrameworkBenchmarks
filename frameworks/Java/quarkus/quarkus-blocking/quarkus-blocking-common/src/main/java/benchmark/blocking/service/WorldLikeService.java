package benchmark.blocking.service;

import benchmark.model.WorldLike;

import java.util.List;

public interface WorldLikeService {
    WorldLike findFortuneById(int id);

    List<WorldLike> update(List<WorldLike> worlds);
}
