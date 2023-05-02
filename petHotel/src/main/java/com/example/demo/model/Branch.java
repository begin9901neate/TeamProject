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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.*;

/**
 * Branch generated by hbm2java
 */
@Entity
@Table(name = "branch", catalog = "pet_hotel_system")
public class Branch implements java.io.Serializable {

	private Integer id;
	private String name;
	private String address;
	private String cellphone;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private List<Rooms> roomses = new ArrayList<>(0);
	
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

	public Branch() {
	}

	public Branch(String name, String address, String cellphone, Date createdAt) {
		this.name = name;
		this.address = address;
		this.cellphone = cellphone;
		this.createdAt = createdAt;
	}

	public Branch(String name, String address, String cellphone, Date createdAt, Date updatedAt, Date deletedAt,
			List<Rooms> roomses) {
		this.name = name;
		this.address = address;
		this.cellphone = cellphone;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.roomses = roomses;
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

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "address", nullable = false, length = 45)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "cellphone", nullable = false, length = 45)
	public String getCellphone() {
		return this.cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "branch")
	public List<Rooms> getRoomses() {
		return this.roomses;
	}

	public void setRoomses(List<Rooms> roomses) {
		this.roomses = roomses;
	}

}
