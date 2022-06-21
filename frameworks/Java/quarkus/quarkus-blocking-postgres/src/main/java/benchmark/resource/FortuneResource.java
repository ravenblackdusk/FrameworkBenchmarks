package benchmark.resource;

import benchmark.model.Fortune;
import benchmark.service.FortuneService;
import io.quarkus.qute.Template;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;

@Path("/fortunes")
public class FortuneResource {
    @Inject
    FortuneService fortuneService;
    @Inject
    Template fortunes;

    @Produces("text/html; charset=UTF-8")
    @GET
    public String fortunes() {
        return fortunes.data("fs", Stream.concat(fortuneService.findAll().stream(),
                        Stream.of(fortuneService.create(0, "Additional fortune added at request time.")))
                .sorted(comparing(Fortune::getMessage))).render();
    }
}
