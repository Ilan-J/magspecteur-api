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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_theme")
	private Theme theme;

	public Article() {}

	public Article(String name, String author, Theme theme) {
		this.name = name;
		this.author = author;
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

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}
}
