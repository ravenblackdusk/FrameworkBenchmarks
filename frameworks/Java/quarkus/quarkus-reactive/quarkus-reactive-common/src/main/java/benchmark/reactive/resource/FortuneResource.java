package benchmark.reactive.resource;

import benchmark.model.FortuneLike;
import benchmark.reactive.service.FortuneLikeService;
import io.quarkus.qute.Template;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

@Path("/fortunes")
public class FortuneResource {
    @Inject
    FortuneLikeService fortuneService;
    @Inject
    Template fortunes;

    @Produces("text/html; charset=UTF-8")
    @GET
    public Uni<String> fortunes() {
        return Uni.combine().all().unis(fortuneService.findAllFortunes(),
                        Uni.createFrom().item(fortuneService.create(0, "Additional fortune added at request time."))).asTuple()
                .flatMap(tuple -> fortunes.data("fs",
                        Stream.concat(tuple.getItem1().stream(), Stream.of(tuple.getItem2()))
                                .sorted(comparing(FortuneLike::getMessage))).createUni());
    }
}