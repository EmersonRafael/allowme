package com.tempest.teste.allowme.entity;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="billings")
public class Billings {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", nullable = false)
	private Long id;
	
	@Column(name="created_at", nullable = false)
	private LocalDateTime created;
	
	@Column(name="start_date", nullable = true)
	private Date startDate;
	
	@Column(name="end_date", nullable = true)
	private Date endDate;
	
	@Column(name="total_price",  columnDefinition = "Decimal(10,6) default '0'", nullable = false)
	private double totalPrice;
	
	public Billings() {}
	
	public Billings(Long id, LocalDateTime created, Date startDate, Date endDate, double totalPrice) {
		this.id = id;
		this.created = created;
		this.startDate = startDate;
		this.endDate = endDate;
		this.totalPrice = totalPrice;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
}
