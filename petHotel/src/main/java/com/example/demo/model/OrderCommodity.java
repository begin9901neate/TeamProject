package com.example.demo.model;
// Generated 2023年4月13日 下午9:55:39 by Hibernate Tools 3.6.2.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * OrderCommodity generated by hbm2java
 */
@Entity
@Table(name = "order_commodity", catalog = "pet_hotel_system")
public class OrderCommodity implements java.io.Serializable {

	private Integer id;
	private Users users;
	private Commodity commodity;
	private int count;
	private int amount;
	private String paymentMethod;
	private int complete;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private Set<CustomerResponse> customerResponses = new HashSet<>(0);
	
	@PrePersist
	protected void onCreate() {
	    createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
	    updatedAt = new Date();
	}

	@PreRemove
	protected void onDelete() {
	    deletedAt = new Date();
	}

	public OrderCommodity() {
	}

	public OrderCommodity(Users users, Commodity commodity, int count, int amount, String paymentMethod, int complete) {
		this.users = users;
		this.commodity = commodity;
		this.count = count;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.complete = complete;
	}

	public OrderCommodity(Users users, Commodity commodity, int count, int amount, String paymentMethod, int complete,
			Date createdAt, Date updatedAt, Date deletedAt, Set<CustomerResponse> customerResponses) {
		this.users = users;
		this.commodity = commodity;
		this.count = count;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.complete = complete;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.customerResponses = customerResponses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "commodity_id", nullable = false)
	public Commodity getCommodity() {
		return this.commodity;
	}

	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}

	@Column(name = "count", nullable = false)
	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Column(name = "amount", nullable = false)
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Column(name = "payment_method", nullable = false, length = 45)
	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "complete", nullable = false)
	public int getComplete() {
		return this.complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", length = 19)
	public Date getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_at", length = 19)
	public Date getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deleted_at", length = 19)
	public Date getDeletedAt() {
		return this.deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderCommodity")
	public Set<CustomerResponse> getCustomerResponses() {
		return this.customerResponses;
	}

	public void setCustomerResponses(Set<CustomerResponse> customerResponses) {
		this.customerResponses = customerResponses;
	}

}
