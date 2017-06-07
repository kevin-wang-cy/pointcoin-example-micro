package com.upbchain.pointcoin.examplemicro.api.resource;

import com.upbchain.pointcoin.examplemicro.api.model.Echo;
import com.upbchain.pointcoin.examplemicro.api.service.EchoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Optional;

@Component
@Path("/echo")
public class EchoResource {
    private static final Logger LOG = LoggerFactory.getLogger(EchoResource.class);

    @Autowired
    private EchoService echoService;

    @GET
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEchoByUUID(@PathParam("uuid") String uuid) {
        Echo echo = echoService.findByUUID(uuid);

        if (echo == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(uuid).build();
        }

        return Response.ok(echo).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createEcho(@NotNull Echo echo, @Context UriInfo uriInfo) {
        try {
            Echo result = echoService.createEcho(echo);

            return Response.created(uriInfo.getAbsolutePathBuilder().path(result.getUuid()).build()).entity(result).build();
        } catch (RuntimeException ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Echo> retrieveAllEchoes() {
        return echoService.retrieveAllEchoes();
    }
/*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Echo> retrieveAllEchoesBySender(@QueryParam("from") String from) {
        return echoService.retrieveAllBySender(from);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Echo> retrieveAllEchoesBySenderAndReciever(@QueryParam("from") String from, @QueryParam("to") String to) {
        return echoService.retrieveAllFromAtoB(from, to);
    }
    */
}