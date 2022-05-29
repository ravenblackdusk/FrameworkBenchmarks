package benchmark.service;

import benchmark.model.WorldLike;

import java.util.List;

public interface WorldLikeService<W extends WorldLike> {
    W findById(int id);

    List<W> update(List<W> worlds);
}
