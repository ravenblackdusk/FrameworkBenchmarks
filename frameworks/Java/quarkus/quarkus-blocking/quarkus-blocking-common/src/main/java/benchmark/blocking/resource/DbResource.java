package benchmark.blocking.resource;

import benchmark.blocking.service.WorldLikeService;
import benchmark.model.WorldLike;
import benchmark.util.Util;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class DbResource {
    @Inject
    WorldLikeService worldService;

    private Stream<WorldLike> getWorldStream(String queries) {
        return Util.randomIntStream().limit(Util.parseQueryCount(queries)).mapToObj(worldService::findFortuneById);
    }

    @GET
    @Path("db")
    public WorldLike db() {
        return worldService.findFortuneById(Util.randomInt());
    }

    @GET
    @Path("queries")
    public List<WorldLike> getQueries(@QueryParam("queries") String queries) {
        return getWorldStream(queries).collect(toList());
    }

    @GET
    @Path("updates")
    public List<WorldLike> updates(@QueryParam("queries") String queries) {
        return worldService.update(
                getWorldStream(queries).peek(world -> world.setRandomnumber(Util.randomInt())).collect(toList()));
    }
}
