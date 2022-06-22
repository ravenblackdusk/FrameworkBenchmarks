package benchmark.reactive.resource;

import benchmark.model.World;
import benchmark.reactive.service.WorldLikeService;
import benchmark.util.Util;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Path("/")
public class DbResource {
    @Inject
    WorldLikeService worldService;

    private Stream<Uni<World>> getWorldStream(String queries) {
        return Util.randomIntStream().limit(Util.parseQueryCount(queries)).mapToObj(worldService::findFortuneById);
    }

    @GET
    @Path("db")
    public Uni<World> db() {
        return worldService.findFortuneById(Util.randomInt());
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
            it.setRandomnumber(Util.randomInt());
            return it;
        })).collect(toList()));
    }
}
