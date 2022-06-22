package benchmark.reactive.jdbc.postgres.service;

import benchmark.reactive.jdbc.service.AbstractFortuneService;
import io.vertx.mutiny.pgclient.PgPool;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FortuneService extends AbstractFortuneService {
    @Inject
    PgPool pool;

    @PostConstruct
    public void init() {
        setPool(pool);
    }
}
