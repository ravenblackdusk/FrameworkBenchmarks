package benchmark.common.web;

import benchmark.common.model.WorldLike;
import benchmark.common.service.WorldLikeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

@RestController
public class WorldController<W extends WorldLike> {
    private static final int MIN = 1;
    private static final int MAX = 10001;
    private final WorldLikeService<W> worldService;

    public WorldController(WorldLikeService<W> worldService) {
        this.worldService = worldService;
    }

    private static int randomInt() {
        return ThreadLocalRandom.current().nextInt(MIN, MAX);
    }

    private static IntStream randomIntStream() {
        return ThreadLocalRandom.current().ints(MIN, MAX).distinct();
    }

    private static int parseQueryCount(String textValue) {
        if (textValue == null) {
            return 1;
        }
        int parsedValue;
        try {
            parsedValue = Integer.parseInt(textValue);
        } catch (NumberFormatException e) {
            return 1;
        }
        return Math.min(500, Math.max(1, parsedValue));
    }

    @GetMapping("/plaintext")
    public Mono<String> getPlainText() {
        return Mono.just("Hello, World!");
    }

    @GetMapping("/json")
    public Mono<Map<String, String>> getJson() {
        return Mono.just(Map.of("message", "Hello, World!"));
    }

    @GetMapping("/db")
    public Mono<W> getDb() {
        return worldService.findById(randomInt());
    }

    @GetMapping("/queries")
    public Flux<W> getQueries(String queries) {
        return Flux.fromStream(randomIntStream().limit(parseQueryCount(queries)).boxed())
                .flatMap(worldService::findById);
    }

    @GetMapping("/updates")
    public Flux<W> getUpdates(String queries) {
        return getQueries(queries).map(it -> {
            it.setRandomnumber(randomInt());
            return it;
        }).flatMap(worldService::save);
    }
}
