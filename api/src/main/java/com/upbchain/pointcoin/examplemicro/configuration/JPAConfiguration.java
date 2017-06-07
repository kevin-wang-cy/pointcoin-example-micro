package com.upbchain.pointcoin.examplemicro.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JPAConfiguration {
	private static final Logger LOG = LoggerFactory.getLogger(JPAConfiguration.class);
	protected static final String USER_INTERNAL_SERVICE = "__service_example_micro__";

	@Bean
	public AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}
	
	private static class AuditorAwareImpl implements AuditorAware<String> {

		/*
		 * (non-Javadoc)
		 * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
		 */
		public String getCurrentAuditor() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null || !authentication.isAuthenticated()) {
	            return USER_INTERNAL_SERVICE;
	        }
			
			return authentication.getName();
		}
	}

}
