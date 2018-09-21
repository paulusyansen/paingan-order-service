package org.paingan.order.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="p_order")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="order_id")
	public Long id;
	
	@Column(name="order_qty")
	public int quantity;
	
	public int memberId;
	
	public int productId;
	
	public int port;

	public Order() {
		super();
	}

	public Order(Long id, int quantity, int memberId, int productId) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.memberId = memberId;
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", quantity=" + quantity + ", memberId=" + memberId + ", productId=" + productId
				+ "]";
	}

}
