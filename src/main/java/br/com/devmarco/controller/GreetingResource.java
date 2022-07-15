package br.com.devmarco.controller;

import br.com.devmarco.config.MessageConfig;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    MessageConfig messageConfig;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> hello() {
        return Uni.createFrom().item(messageConfig.getMensagem1() + messageConfig.getMensagem2()    );
    }
}