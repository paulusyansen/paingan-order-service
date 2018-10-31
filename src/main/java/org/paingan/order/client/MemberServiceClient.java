package org.paingan.order.client;

import org.paingan.order.domain.MemberDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@FeignClient(name="paingan-member-service", configuration = MemberServiceClient.FeignClientConfiguration.class)
//@RibbonClient("paingan-member-service")
public interface MemberServiceClient {
	public static final String AUTHORIZATION_HEADER= "Authorization";
    public static final String BEARER_TOKEN_TYPE = "Bearer";
    
    
    
    
	@GetMapping("/id/{id}")
	public @ResponseBody MemberDTO getMember(@PathVariable("id") Long id);
		
	
	@Configuration("profileIdFeignClientConfiguration")
    class FeignClientConfiguration {
        @Bean
        public BearerHeaderAuthRequestInterceptor bearerHeaderAuthRequestInterceptor() {
            return new BearerHeaderAuthRequestInterceptor();
        }
    }

    class BearerHeaderAuthRequestInterceptor implements RequestInterceptor {
		@Override
		public void apply(RequestTemplate template) {
			SecurityContext securityContext = SecurityContextHolder.getContext();
	        Authentication authentication = securityContext.getAuthentication();

	        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
	            OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
	            template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, details.getTokenValue()));
	        }
		}

    }
}
