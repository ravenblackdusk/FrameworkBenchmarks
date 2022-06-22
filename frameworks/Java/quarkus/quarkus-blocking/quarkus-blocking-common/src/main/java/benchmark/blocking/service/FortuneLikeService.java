package benchmark.blocking.service;

import benchmark.model.FortuneLike;

import java.util.List;

public interface FortuneLikeService {
    List<? extends FortuneLike> findAllFortunes();

    FortuneLike create(int id, String message);
}
