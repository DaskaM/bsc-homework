package com.bsc.notes.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

/**
 * This class extends springboot security configuration {@link WebSecurityConfiguration} and allows to add custom rules.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String DEVELOPMENT = "dev";

    private Environment environment;

    @Autowired
    public WebSecurityConfiguration(Environment environment) {
        this.environment = environment;
    }

    /**
     * This method is checking whether the application is executed in a development environment, and if it is,
     * it allows CORS for the development purposes
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        boolean isDevelopment = false;

        for (String profile : environment.getActiveProfiles()) {
            if (profile.equals(DEVELOPMENT)) {
                isDevelopment = true;
                break;
            }
        }

        if (isDevelopment) {
            final CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
            corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
            corsConfiguration.addAllowedMethod(HttpMethod.PUT);

            httpSecurity
                    .authorizeRequests()
                    .antMatchers("/").permitAll().and()
                    .authorizeRequests().antMatchers("/h2-console/**").permitAll();
            httpSecurity.csrf().disable();
            httpSecurity.headers().frameOptions().disable();
            httpSecurity.cors().configurationSource(request -> corsConfiguration);
        }
    }

}
