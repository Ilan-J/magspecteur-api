package com.magspecteur.api.domain;

import jakarta.persistence.*;

@Entity
public class Theme {

	@Id
	@Column(name = "pk_theme")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	public Theme() {}

	public Theme(String name) {
		this.name = name;
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
}
