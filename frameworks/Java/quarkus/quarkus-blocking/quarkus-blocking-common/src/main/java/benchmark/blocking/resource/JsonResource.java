package benchmark.blocking.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Map;

@Path("/json")
public class JsonResource {
    @GET
    public Map<String, String> json() {
        return Map.of("message", "Hello, World!");
    }
}
