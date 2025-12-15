package org.com.orderservice.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table
@Entity(name = "order_history")
public class OrderHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	private Long orderId;
	private String fromState;
	private String toState;
	private String reason;
	@Column(columnDefinition = "DATETIME")
	private LocalDateTime changedAt;
	public OrderHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderHistory(Long id, Long orderId, String fromState, String toState, String reason,
			LocalDateTime changedAt) {
		super();
		this.id = id;
		this.orderId = orderId;
		this.fromState = fromState;
		this.toState = toState;
		this.reason = reason;
		this.changedAt = changedAt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getFromState() {
		return fromState;
	}
	public void setFromState(String fromState) {
		this.fromState = fromState;
	}
	public String getToState() {
		return toState;
	}
	public void setToState(String toState) {
		this.toState = toState;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public LocalDateTime getChangedAt() {
		return changedAt;
	}
	public void setChangedAt(LocalDateTime changedAt) {
		this.changedAt = changedAt;
	}
	@Override
	public String toString() {
		return "OrderHistory [id=" + id + ", orderId=" + orderId + ", fromState=" + fromState + ", toState=" + toState
				+ ", reason=" + reason + ", changedAt=" + changedAt + ", getId()=" + getId() + ", getOrderId()="
				+ getOrderId() + ", getFromState()=" + getFromState() + ", getToState()=" + getToState()
				+ ", getReason()=" + getReason() + ", getChangedAt()=" + getChangedAt() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
