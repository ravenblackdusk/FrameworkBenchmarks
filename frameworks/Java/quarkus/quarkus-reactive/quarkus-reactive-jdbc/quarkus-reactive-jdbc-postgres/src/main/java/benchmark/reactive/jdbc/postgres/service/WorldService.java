package benchmark.reactive.jdbc.postgres.service;

import benchmark.reactive.jdbc.service.AbstractWorldService;
import io.vertx.mutiny.pgclient.PgPool;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class WorldService extends AbstractWorldService {
    @Inject
    PgPool pool;

    @PostConstruct
    public void init() {
        setPool(pool);
    }

    @Override
    protected String firstParameterPlaceHolder() {
        return "$1";
    }

    @Override
    protected String secondParameterPlaceHolder() {
        return "$2";
    }
}
