package io.quarkus.benchmark.service;

import io.quarkus.benchmark.model.Fortune;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowIterator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FortuneService {
    @Inject
    PgPool pgPool;

    public Uni<List<Fortune>> findAll() {
        return pgPool.query("select * from fortune").execute().map(rows -> {
            RowIterator<Row> iterator = rows.iterator();
            List<Fortune> fortunes = new ArrayList<>();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                fortunes.add(new Fortune(row.getInteger(0), row.getString(1)));
            }
            return fortunes;
        });
    }

    public Fortune create(int id, String message) {
        return new Fortune(id, message);
    }
}
