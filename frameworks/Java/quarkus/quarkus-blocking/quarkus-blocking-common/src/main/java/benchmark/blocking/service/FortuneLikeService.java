package benchmark.blocking.service;

import benchmark.model.Fortune;

import java.util.List;

public interface FortuneLikeService {
    List<? extends Fortune> findAllFortunes();

    Fortune create(int id, String message);
}
