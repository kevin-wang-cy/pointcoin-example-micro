package com.upbchain.pointcoin.examplemicro.api.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Component
@Path("/echo")
public class EchoResource {
    private static final Logger LOG = LoggerFactory.getLogger(EchoResource.class);

    @GET
    public String message() {
        return "Hello";
    }
}