package benchmark.reactive.jdbc.mysql.service;

import benchmark.reactive.jdbc.service.AbstractFortuneService;
import io.vertx.mutiny.mysqlclient.MySQLPool;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FortuneService extends AbstractFortuneService {
    @Inject
    MySQLPool pool;

    @PostConstruct
    public void init() {
        setPool(pool);
    }
}
