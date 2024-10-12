package com.magspecteur.api.domain;

import jakarta.persistence.*;

@Entity
public class Article {

	@Id
	@Column(name = "pk_article")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String author;

	@Column(name = "fk_magazine")
	private Integer magazineId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_theme")
	private Theme theme;

	public Article() {}

	public Article(String name, String author, Integer magazineId, Theme theme) {
		this.name = name;
		this.author = author;
		this.magazineId = magazineId;
		this.theme = theme;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getMagazineId() {
		return magazineId;
	}

	public void setMagazineId(Integer magazineId) {
		this.magazineId = magazineId;
	}

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
}
