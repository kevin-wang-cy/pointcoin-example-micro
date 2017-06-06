package com.upbchain.pointcoin.examplemicro.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @author kevin.wang.cy@gmail.com
 */
@Configuration
public class ResourceServerConfiguration {
    private static final Logger LOG = LoggerFactory.getLogger(ResourceServerConfiguration.class);
    private static final String RESOURCE_ID_EXAMPLE_MICRO = "example-micro";

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfig extends ResourceServerConfigurerAdapter {
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
           if (LOG.isDebugEnabled()) {
               LOG.debug("Configuring ResourceServerSecurityConfigurer...");
            }

            resources.resourceId(RESOURCE_ID_EXAMPLE_MICRO);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Configuring ResourceServer HttpSecurity...");
            }

            // @formatter:off
            http.antMatcher("/**")
                    .authorizeRequests()
                        .anyRequest().authenticated();
            // @formatter:on
        }
    }

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
    protected static class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            // @formatter:off
            http
                    .authorizeRequests().anyRequest().authenticated()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
            // @formatter:on
        }
    }
}
