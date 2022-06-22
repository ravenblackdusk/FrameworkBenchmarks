package benchmark.reactive.jdbc.service;

import benchmark.model.WorldLike;
import benchmark.reactive.jdbc.model.World;
import benchmark.reactive.service.WorldLikeService;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Pool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowIterator;
import io.vertx.mutiny.sqlclient.Tuple;

import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class AbstractWorldService implements WorldLikeService {
    private Pool pool;

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    protected abstract String firstParameterPlaceHolder();

    protected abstract String secondParameterPlaceHolder();

    @Override
    public Uni<WorldLike> findFortuneById(int id) {
        return pool.preparedQuery("select * from world where id = " + firstParameterPlaceHolder()).execute(Tuple.of(id))
                .onItem().transform(rows -> {
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
    public Uni<List<WorldLike>> update(List<Uni<WorldLike>> worlds) {
        return Uni.join().all(worlds).andFailFast().flatMap(worldsList -> pool.preparedQuery(
                        "update world set randomnumber = " + firstParameterPlaceHolder() + " where id = "
                                + secondParameterPlaceHolder()).executeBatch(
                        worldsList.stream().map(it -> Tuple.of(it.getRandomnumber(), it.getId())).collect(toList()))
                .map(rows -> worldsList));
    }
}
