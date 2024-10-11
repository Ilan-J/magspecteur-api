package com.magspecteur.api.domain;

import jakarta.persistence.*;

import java.time.Instant;
import java.util.Collection;
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

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "publisher_themes",
			joinColumns = @JoinColumn(name = "fk_publisher"),
			inverseJoinColumns = @JoinColumn(name = "fk_theme")
	)
	private Collection<Theme> themes;

	public Publisher() {}

	public Publisher(String name, String address, Collection<Theme> themes) {
		this.name = name;
		this.address = address;
		this.creation = Date.from(Instant.now());
		this.themes = themes;
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

	public Collection<Theme> getThemes() {
		return themes;
	}

	public void setThemes(Collection<Theme> themes) {
		this.themes = themes;
	}
}
