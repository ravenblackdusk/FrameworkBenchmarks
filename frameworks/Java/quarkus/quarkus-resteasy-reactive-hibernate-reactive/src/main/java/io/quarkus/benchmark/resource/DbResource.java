package io.quarkus.benchmark.resource;

import io.quarkus.benchmark.model.World;
import io.quarkus.benchmark.service.WorldService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class DbResource {
    private static final int MIN = 1;
    private static final int MAX = 10001;

    private static int randomInt() {
        return ThreadLocalRandom.current().nextInt(MIN, MAX);
    }

    private static IntStream randomIntStream() {
        return ThreadLocalRandom.current().ints(MIN, MAX).distinct();
    }

    @Inject
    WorldService worldService;

    private Stream<Uni<World>> getWorldStream(String queries) {
        return randomIntStream().limit(parseQueryCount(queries)).mapToObj(worldService::findById);
    }

    @GET
    @Path("db")
    public Uni<World> db() {
        return worldService.findById(randomInt());
    }

    @GET
    @Path("queries")
    public Uni<List<World>> getQueries(@QueryParam("queries") String queries) {
        return Uni.join().all(getWorldStream(queries).collect(toList())).andFailFast();
    }

    @GET
    @Path("updates")
    public Uni<List<World>> updates(@QueryParam("queries") String queries) {
        return worldService.update(getWorldStream(queries).map(world -> world.map(it -> {
            it.setRandomnumber(randomInt());
            return it;
        })));
    }

    private int parseQueryCount(String textValue) {
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
}
