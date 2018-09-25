package org.paingan.order.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityResourceConfig extends ResourceServerConfigurerAdapter{

	public static final String RESOURCE_ID = "member";
	   
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
	        .csrf()
	        .disable()
	        .headers()
	        .frameOptions()
	        .disable()
	        	.and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/order/**").authenticated()
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll();
                
                //.access("#oauth2.hasScope('read')")
                //.and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }
   
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
         resources.resourceId(RESOURCE_ID);
    }
	
}
