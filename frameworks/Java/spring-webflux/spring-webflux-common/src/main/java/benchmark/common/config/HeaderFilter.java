package benchmark.common.config;

import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

import static java.time.format.DateTimeFormatter.RFC_1123_DATE_TIME;

public class HeaderFilter implements WebFilter {
    private static volatile String date = getDate();

    private static String getDate() {
        return RFC_1123_DATE_TIME.format(ZonedDateTime.now());
    }

    @Scheduled(fixedRate = 1000)
    void updateDate() {
        date = getDate();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        HttpHeaders headers = exchange.getResponse().getHeaders();
        headers.add("Server", "Webflux");
        headers.add("Date", date);
        return chain.filter(exchange);
    }
}
