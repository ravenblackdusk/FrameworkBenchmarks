package benchmark.reactive.jdbc.mysql.service;

import benchmark.reactive.jdbc.service.AbstractWorldService;
import io.vertx.mutiny.mysqlclient.MySQLPool;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class WorldService extends AbstractWorldService {
    @Inject
    MySQLPool pool;

    @PostConstruct
    public void init() {
        setPool(pool);
    }

    @Override
    protected String firstParameterPlaceHolder() {
        return "?";
    }

    @Override
    protected String secondParameterPlaceHolder() {
        return "?";
    }
}
