package benchmark.filter;

import io.quarkus.scheduler.Scheduled;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpHeaders;
import org.jboss.resteasy.reactive.server.ServerResponseFilter;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedMap;
import java.time.ZonedDateTime;

import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;

@ApplicationScoped
public class ServerHeaderFilter {
    private static volatile CharSequence date = getDate();

    private static CharSequence getDate() {
        return HttpHeaders.createOptimized(RFC_1123_DATE_TIME.format(ZonedDateTime.now()));
    }

    @Scheduled(every = "1s")
    void updateDate() {
        date = getDate();
    }

    @ServerResponseFilter
    public Uni<Void> filter(ContainerResponseContext response) {
        MultivaluedMap<String, Object> headers = response.getHeaders();
        headers.add("Server", "Quarkus");
        headers.add("Date", date);
        return Uni.createFrom().voidItem();
    }
}
