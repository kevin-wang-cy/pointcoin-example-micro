package com.upbchain.pointcoin.examplemicro.api.resource;

import com.upbchain.pointcoin.examplemicro.api.model.Echo;
import com.upbchain.pointcoin.examplemicro.api.service.EchoService;
import io.swagger.annotations.*;
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
@Api(value = "Echo resource", produces = "application/json")
public class EchoResource {
    private static final Logger LOG = LoggerFactory.getLogger(EchoResource.class);

    @Autowired
    private EchoService echoService;

    @GET
    @Path("/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Gets a particular Echo by it's unique identifier", response = Echo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Echo resource found"),
            @ApiResponse(code = 404, message = "Echo resource not found")
    })
    public Response getEchoByUUID(@ApiParam(value = "Echo's uuid", required = true) @PathParam("uuid") String uuid) {
        Echo echo = echoService.findByUUID(uuid);

        if (echo == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(uuid).build();
        }

        return Response.ok(echo).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create an Echo resource")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Echo resource created", response = Echo.class, responseHeaders = {
                    @ResponseHeader(name = "Location", description = "The URL to retrieve created Echo resource", response = String.class)
            }),
            @ApiResponse(code = 500, message = "Exception while creating Echo resource.", response = Exception.class)
    })
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