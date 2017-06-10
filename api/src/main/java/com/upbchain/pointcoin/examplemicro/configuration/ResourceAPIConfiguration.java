package com.upbchain.pointcoin.examplemicro.configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Configuration
@ApplicationPath("/api")
public class ResourceAPIConfiguration extends ResourceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(ResourceAPIConfiguration.class);

    private static final String RESOURCE_PACKAGE = "com.upbchain.pointcoin.examplemicro.api.resource";
    private static final String MODEL_PACKAGE = "com.upbchain.pointcoin.examplemicro.api.model";

    @Autowired
    public ResourceAPIConfiguration(ObjectMapper objectMapper) {
        // API EndPoints Registration
        registerEndPoints();

        // register jackson for json
        register(new ObjectMapperContextResolver(objectMapper));
    }

    @PostConstruct
    public void init() {
        this.configureSwagger();
    }

    private void registerEndPoints() {
        packages(RESOURCE_PACKAGE);
    }

    private void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("springboot-jersey-swagger-docker-example");
        config.setTitle("Spring Boot + Jersey + Swagger + Docker Example");
        config.setVersion("v1");
        config.setContact("Kevin Wang kevin.wang.cy@gmail.com");
        config.setSchemes(new String[] { "http", "https" });
        config.setBasePath("/api");
        config.setResourcePackage(RESOURCE_PACKAGE);
        config.setPrettyPrint(true);
        config.setScan(true);
    }

    @Provider
    public static class ObjectMapperContextResolver implements ContextResolver<ObjectMapper> {

        private final ObjectMapper mapper;

        public ObjectMapperContextResolver(ObjectMapper mapper) {
            this.mapper = mapper;

            mapper.findAndRegisterModules();

            mapper.setSerializationInclusion(Include.NON_NULL);

            mapper.enable(Feature.WRITE_BIGDECIMAL_AS_PLAIN);
            mapper.enable(SerializationFeature.INDENT_OUTPUT, SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, SerializationFeature.CLOSE_CLOSEABLE);
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, SerializationFeature.WRITE_NULL_MAP_VALUES, SerializationFeature.FAIL_ON_EMPTY_BEANS);
        }

        @Override
        public ObjectMapper getContext(Class<?> type) {
            return mapper;
        }
    }
}
