package io.quarkus.benchmark.service;

import io.quarkus.benchmark.model.World;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowIterator;
import io.vertx.mutiny.sqlclient.Tuple;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@ApplicationScoped
public class WorldService {
    @Inject
    PgPool pgPool;

    public Uni<World> findById(int id) {
        return pgPool.preparedQuery("select * from world where id = $1").execute(Tuple.of(id)).onItem()
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

    public Uni<List<World>> update(Stream<Uni<World>> worlds) {
        return Uni.join().all(worlds.collect(toList())).andFailFast().flatMap(
                worldsList -> pgPool.preparedQuery("update world set randomnumber = $1 where id = $2").executeBatch(
                                worldsList.stream().map(it -> Tuple.of(it.getRandomnumber(), it.getId())).collect(toList()))
                        .map(rows -> worldsList));
    }
}
