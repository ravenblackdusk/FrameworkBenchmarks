package benchmark.web;

import benchmark.model.WorldLike;
import benchmark.service.WorldLikeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

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
    public String getPlainText() {
        return "Hello, World!";
    }

    @GetMapping("/json")
    public Map<String, String> getJson() {
        return Map.of("message", "Hello, World!");
    }

    @GetMapping("/db")
    public W getDb() {
        return worldService.findById(randomInt());
    }

    @GetMapping("/queries")
    public List<W> getQueries(String queries) {
        return randomIntStream().limit(parseQueryCount(queries)).mapToObj(worldService::findById).collect(toList());
    }

    @GetMapping("/updates")
    public List<W> getUpdates(String queries) {
        return worldService.update(
                getQueries(queries).stream().peek(it -> it.setRandomnumber(randomInt())).collect(toList()));
    }
}
