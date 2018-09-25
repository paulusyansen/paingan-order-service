package org.paingan.order.controller;

import java.util.Optional;

import org.paingan.order.client.MemberServiceClient;
import org.paingan.order.domain.MemberDTO;
import org.paingan.order.domain.Order;
import org.paingan.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class OrderController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private MemberServiceClient  memberServiceProxy;
	
	@Autowired
	private Environment environment;

	@GetMapping(value="/api/order/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		
		 Optional<Order> order = orderRepository.findById(id);
		 
		 if(order.isPresent()) {
			 order.get().setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			 return ResponseEntity.ok(order.get());
		 }
		 
		 log.info("{}",order);
		 return ResponseEntity.ok(new Order());
	}
	@GetMapping(value="/api/order/member/{id}")
	public ResponseEntity<MemberDTO> findByMemberId(@PathVariable Long id) {
		
		MemberDTO member = memberServiceProxy.getMember(id);
		
		return ResponseEntity.ok(member);
	}
}
