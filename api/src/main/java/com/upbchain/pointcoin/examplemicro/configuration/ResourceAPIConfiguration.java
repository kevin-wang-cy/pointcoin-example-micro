package com.upbchain.pointcoin.examplemicro.configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

@Configuration
@ApplicationPath("/api")
public class ResourceAPIConfiguration extends ResourceConfig {
    private static final Logger LOG = LoggerFactory.getLogger(ResourceAPIConfiguration.class);

    @Autowired
    public ResourceAPIConfiguration(ObjectMapper objectMapper) {
        // register endpoints
        packages("com.upbchain.pointcoin.examplemicro.api.resource");

        // register jackson for json
        register(new ObjectMapperContextResolver(objectMapper));
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
