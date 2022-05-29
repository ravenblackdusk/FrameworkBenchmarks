package benchmark.service;

import benchmark.model.FortuneLike;

import java.util.List;

public interface FortuneLikeService<F extends FortuneLike> {
    List<F> findAll();

    F create(int id, String message);
}
