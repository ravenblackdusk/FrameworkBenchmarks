package io.quarkus.benchmark.resource;

import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/plaintext")
public class PlaintextResource {
    @Produces(MediaType.TEXT_PLAIN)
    @GET
    public Uni<String> plaintext() {
        return Uni.createFrom().item("Hello, world!");
    }
}
