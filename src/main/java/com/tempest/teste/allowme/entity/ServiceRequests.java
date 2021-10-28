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
@Table(name="service_requests")
public class ServiceRequests {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;
	
	@Column(name="created_at", nullable = false)
	private LocalDateTime created;
	
	@ManyToOne
	@JoinColumn(name="service_id", referencedColumnName = "id")
	private Services servico;
	
	@Column(name="status_code", nullable = false)
	private int statusCode;

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

	public Services getServico() {
		return servico;
	}

	public void setServico(Services servico) {
		this.servico = servico;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
}
