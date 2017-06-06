package com.upbchain.pointcoin.examplemicro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * 
 * @author kevin.wang.cy@gmail.com
 *
 */
@SpringBootApplication
public class ExampleMicroApplication {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleMicroApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ExampleMicroApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Let's inspect the beans provided by Spring Boot:");

                String[] beanNames = ctx.getBeanDefinitionNames();
                Arrays.sort(beanNames);
                for (String beanName : beanNames) {
                    LOG.debug(beanName);
                }
            }
        };
    }
}
