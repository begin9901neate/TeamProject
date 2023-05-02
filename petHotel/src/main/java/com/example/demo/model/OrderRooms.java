package com.example.demo.model;
// Generated 2023年4月13日 下午9:55:39 by Hibernate Tools 3.6.2.Final

import java.util.*;

import javax.persistence.CascadeType;
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
 * OrderRooms generated by hbm2java
 */
@Entity
@Table(name = "order_rooms", catalog = "pet_hotel_system")
public class OrderRooms implements java.io.Serializable {

	private Integer id;
	private Users users;
	private Rooms rooms;
	private String paymentMethod;
	private int amount;
	private int complete;
	private String deletedReason;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private List<RoomSession> roomSession = new ArrayList<>(0);
	private List<CustomerResponse> customerResponses = new ArrayList<>(0);
	
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

	public OrderRooms() {
	}

	public OrderRooms(Users users, Rooms rooms, String paymentMethod, int amount, int complete, Date createdAt) {
		this.users = users;
		this.rooms = rooms;
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.complete = complete;
		this.createdAt = createdAt;
	}

	public OrderRooms(Users users, Rooms rooms, String paymentMethod, int amount, int complete, String deletedReason,
			Date createdAt, Date updatedAt, Date deletedAt, List<RoomSession> roomSession, List<CustomerResponse> customerResponses) {
		this.users = users;
		this.rooms = rooms;
		this.paymentMethod = paymentMethod;
		this.amount = amount;
		this.complete = complete;
		this.deletedReason = deletedReason;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.roomSession = roomSession;
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
	@JoinColumn(name = "room_id", nullable = false)
	public Rooms getRooms() {
		return this.rooms;
	}

	public void setRooms(Rooms rooms) {
		this.rooms = rooms;
	}

	@Column(name = "payment_method", nullable = false, length = 45)
	public String getPaymentMethod() {
		return this.paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Column(name = "amount", nullable = false)
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Column(name = "complete", nullable = false)
	public int getComplete() {
		return this.complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	@Column(name = "deleted_reason", length = 45)
	public String getDeletedReason() {
		return this.deletedReason;
	}

	public void setDeletedReason(String deletedReason) {
		this.deletedReason = deletedReason;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, length = 19)
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderRooms", cascade = CascadeType.ALL)
	public List<RoomSession> getRoomSession() {
		return this.roomSession;
	}

	public void setRoomSession(List<RoomSession> roomSession) {
		this.roomSession = roomSession;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orderRooms", cascade = CascadeType.ALL)
	public List<CustomerResponse> getCustomerResponses() {
		return this.customerResponses;
	}

	public void setCustomerResponses(List<CustomerResponse> customerResponses) {
		this.customerResponses = customerResponses;
	}

}