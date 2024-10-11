package com.magspecteur.api.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Date;

@Entity
public class Publisher {

	@Id
	@Column(name = "pk_publisher")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String address;
	@Column(name = "creation_date")
	private Date creation;

	public Publisher() {}

	public Publisher(String name, String address) {
		this.name = name;
		this.address = address;
		this.creation = Date.from(Instant.now());
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getCreation() {
		return creation;
	}
}
