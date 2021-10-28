package com.tempest.teste.allowme.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="billing_summary")
public class BillingSummary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;
	
	@Column(name="created_at", nullable = false)
	private LocalDateTime created;
	
	@Column(name="total_requests", nullable = false)
	private Long totalRequests;
	
	@ManyToOne
	@JoinColumn(name="service_id", referencedColumnName = "id")
	private Services servico;
	
	@ManyToOne
	@JoinColumn(name="billing_id", referencedColumnName = "id")
	private Billings billing;
		
	@Column(name="price_per_request",  columnDefinition = "Decimal(10,6) default '0'", nullable = false)
	private double pricePerRequest;
	
	public BillingSummary() {}
	
	public BillingSummary(Long id, LocalDateTime created, 
					Long totalRequests, Services servico, Billings billing, double pricePerRequest) {
		this.id = id;
		this.created = created;
		this.totalRequests = totalRequests;
		this.servico = servico;
		this.billing = billing;
		this.pricePerRequest = pricePerRequest;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public Long getTotalRequests() {
		return totalRequests;
	}

	public void setTotalRequests(Long totalRequests) {
		this.totalRequests = totalRequests;
	}

	public Services getServico() {
		return servico;
	}

	public void setServico(Services servico) {
		this.servico = servico;
	}

	public Billings getBilling() {
		return billing;
	}

	public void setBilling(Billings billing) {
		this.billing = billing;
	}

	public double getPricePerRequest() {
		return pricePerRequest;
	}

	public void setPricePerRequest(double pricePerRequest) {
		this.pricePerRequest = pricePerRequest;
	}
	
	
}
