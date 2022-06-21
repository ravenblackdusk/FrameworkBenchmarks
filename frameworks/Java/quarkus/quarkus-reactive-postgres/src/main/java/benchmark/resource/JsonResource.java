package benchmark.resource;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Map;

@Path("/json")
public class JsonResource  {
    @GET
    public Uni<Map<String, String>> json() {
        return Uni.createFrom().item(Map.of("message", "Hello, World!"));
    }
}
