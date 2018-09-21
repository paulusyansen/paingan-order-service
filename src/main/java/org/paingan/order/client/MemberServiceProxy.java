package org.paingan.order.client;

import org.paingan.order.domain.MemberDTO;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("paingan-member-service")
@RibbonClient("paingan-member-service")
public interface MemberServiceProxy {

	@GetMapping("/api/member/{id}")
	public @ResponseBody MemberDTO getMember(@PathVariable("id") Long id);
}
