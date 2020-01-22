package com.redhat.labs;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Project {
    @Inject
    JsonWebToken jwt;

    @Inject
    @RestClient
    OMPGitLabAPIService ompGitLabAPIService;

    @ConfigProperty(name = "configRepositoryId",defaultValue = "9407")
    String configRepositoryId;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    public String defaultEndpoint(@Context SecurityContext ctx) {
        Principal caller = ctx.getUserPrincipal();
        String name = caller == null ? "anonymous" : caller.getName();
        String helloReply = String.format("hello + %s, isSecure: %s, authScheme: %s", name, ctx.isSecure(), ctx.getAuthenticationScheme());
        return helloReply;
    }

    @GET
    @Path("open")
    @PermitAll
    public String openEndpoint(@Context SecurityContext ctx) {
        return Json.createObjectBuilder().add("hello", "world").build().toString();
    }

    @GET
    @Path("secure")
    @Produces(MediaType.TEXT_PLAIN)
    public String securedEndpoint(@Context SecurityContext ctx) {
        return jwt.getName();
    }

    @GET
    @Path("config")
    @Produces(MediaType.TEXT_PLAIN)
    @PermitAll
    public String fetchConfigDataFromCache(@Context SecurityContext ctx) {
    //TODO - 🤠 Make this call to cache and not to the GitLab Api thingy to get file directly
        return ompGitLabAPIService.getFile("schema/config.yml", configRepositoryId).readEntity(String.class);
    }


    @POST
    @Path("create")
    public String createNewResidency(@Context SecurityContext ctx, Object request) {
        return ompGitLabAPIService.createNewResidency(request).readEntity(String.class);
    }

// TODO - Add this in when needed #YOLO
//    @Inject
//    ResidencyDataCache residencyDataCache;


}