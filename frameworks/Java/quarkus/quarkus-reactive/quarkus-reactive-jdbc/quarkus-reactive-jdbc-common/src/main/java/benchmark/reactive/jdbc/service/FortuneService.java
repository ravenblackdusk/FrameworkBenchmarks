package benchmark.reactive.jdbc.service;

import benchmark.model.Fortune;
import benchmark.reactive.service.FortuneLikeService;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Pool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowIterator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class FortuneService implements FortuneLikeService {
    @Inject
    Pool pool;

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
