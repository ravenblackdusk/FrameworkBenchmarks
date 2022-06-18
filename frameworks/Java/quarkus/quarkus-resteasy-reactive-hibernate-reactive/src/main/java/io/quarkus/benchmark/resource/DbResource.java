package io.quarkus.benchmark.resource;

import io.quarkus.benchmark.model.World;
import io.quarkus.benchmark.repository.WorldRepository;
import io.smallrye.context.api.CurrentThreadContext;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.context.ThreadContext;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

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
    WorldRepository worldRepository;

    @GET
    @Path("db")
    @CurrentThreadContext(propagated = {}, unchanged = ThreadContext.ALL_REMAINING)
    public Uni<World> db() {
        return worldRepository.findStateless();
    }

    @GET
    @Path("queries")
    @CurrentThreadContext(propagated = {}, unchanged = ThreadContext.ALL_REMAINING)
    public Uni<List<World>> queries(@QueryParam("queries") String queries) {
        final int queryCount = parseQueryCount(queries);
        return worldRepository.findStateless(queryCount);
    }

    @GET
    @Path("updates")
    @CurrentThreadContext(propagated = {}, unchanged = ThreadContext.ALL_REMAINING)
    public Uni<List<World>> updates(@QueryParam("queries") String queries) {
        return Uni.join().all(randomIntStream().limit(parseQueryCount(queries))
                .mapToObj(id -> worldRepository.inStatelessSession(it -> it.get(World.class, id)).map(it -> {
                    it.setRandomnumber(randomInt());
                    return it;
                })).collect(toList())).andFailFast().flatMap(worlds -> worldRepository.inSession(
                it -> it.setBatchSize(worlds.size()).persistAll(worlds.toArray()).map(unused -> worlds)));
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
