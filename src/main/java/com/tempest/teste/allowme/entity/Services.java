package com.tempest.teste.allowme.entity;

import javax.persistence.*;

@Entity
@Table(name="services")
public class Services {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="endpoint", nullable = false)
	private String endPoint;
	
	@Column(name="path", nullable = false)
	private String path;
	
	@Column(name="price_per_request",  columnDefinition = "Decimal(10,6) default '0'", nullable = false)
	private double pricePerRequest;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public double getPricePerRequest() {
		return pricePerRequest;
	}

	public void setPricePerRequest(double pricePerRequest) {
		this.pricePerRequest = pricePerRequest;
	}
	
	
	
	
}
