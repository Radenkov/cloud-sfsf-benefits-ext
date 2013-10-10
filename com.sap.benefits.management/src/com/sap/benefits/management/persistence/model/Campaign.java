package com.sap.benefits.management.persistence.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "CAMPAIGNS", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Campaign implements IDBEntity {
	
	@Expose
	@Id
	@GeneratedValue
	@Column(name = "CAMPAIGN_ID")
	private Long id;
	
	@Expose
	@Basic
	private String name;
	
	@Expose
	@Basic
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Expose
	@Basic
	@Column(name = "END_DATE")
	private Date endDate;
	
	@Expose
	@Basic
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="OWNER_ID")
	private User owner;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "campaign", fetch = FetchType.LAZY, targetEntity = Order.class)
	private Collection<Order> orders;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "campaign", fetch = FetchType.LAZY, targetEntity = UserPoints.class)
	private Collection<UserPoints> userPoints;

	@Override
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Collection<Order> getOrders() {
		if(this.orders == null){
			this.orders = new ArrayList<>();
		}
		return orders;
	}
	
	public void addOrder(Order order){
		getOrders().add(order);
		if(order.getCampaign() != this){
			order.setCampaign(this);
		}
		
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

	public Collection<UserPoints> getUserPoints() {
		if(this.userPoints == null){
			this.userPoints = new ArrayList<>();
		}
		return userPoints;
	}
	
	public void addUserPoints(UserPoints points){
		getUserPoints().add(points);
		if(points.getCampaign() != this){
			points.setCampaign(this);
		}
	}

	public void setUserPoints(Collection<UserPoints> userPoints) {
		this.userPoints = userPoints;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
		if(!owner.getCampaigns().contains(this)){
			owner.addCampaign(this);
		}
	}

}
