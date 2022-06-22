package benchmark.reactive.jdbc.service;

import benchmark.reactive.jdbc.model.Fortune;
import benchmark.reactive.service.FortuneLikeService;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Pool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowIterator;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFortuneService implements FortuneLikeService {
    private Pool pool;

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    @Override
    public Uni<List<Fortune>> findAllFortunes() {
        return pool.query("select * from fortune").execute().map(rows -> {
            RowIterator<Row> iterator = rows.iterator();
            List<Fortune> fortunes = new ArrayList<>();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                fortunes.add(new Fortune(row.getInteger(0), row.getString(1)));
            }
            return fortunes;
        });
    }

    @Override
    public Fortune create(int id, String message) {
        return new Fortune(id, message);
    }
}
