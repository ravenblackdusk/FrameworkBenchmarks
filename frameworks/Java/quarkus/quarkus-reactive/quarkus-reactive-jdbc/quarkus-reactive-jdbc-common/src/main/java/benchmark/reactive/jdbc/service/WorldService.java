package benchmark.reactive.jdbc.service;

import benchmark.model.World;
import benchmark.reactive.service.WorldLikeService;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Pool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowIterator;
import io.vertx.mutiny.sqlclient.Tuple;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class WorldService implements WorldLikeService {
    @Inject
    Pool pool;

    @Override
    public Uni<World> findFortuneById(int id) {
        return pool.preparedQuery("select * from world where id = $1").execute(Tuple.of(id)).onItem()
                .transform(rows -> {
                    RowIterator<Row> iterator = rows.iterator();
                    if (iterator.hasNext()) {
                        Row row = iterator.next();
                        return new World(row.getInteger(0), row.getInteger(1));
                    } else {
                        return null;
                    }
                });
    }

    @Override
    public Uni<List<World>> update(List<Uni<World>> worlds) {
        return Uni.join().all(worlds).andFailFast().flatMap(
                worldsList -> pool.preparedQuery("update world set randomnumber = $1 where id = $2").executeBatch(
                                worldsList.stream().map(it -> Tuple.of(it.getRandomnumber(), it.getId())).collect(toList()))
                        .map(rows -> worldsList));
    }
}
