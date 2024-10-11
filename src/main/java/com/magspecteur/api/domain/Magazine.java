package com.magspecteur.api.domain;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
public class Magazine {

	@Id
	@Column(name = "pk_magazine")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private Integer number;
	@Column(name = "release_date")
	private Date release;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_publisher")
	private Publisher publisher;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "fk_magazine")
	private Collection<Article> articles;

	public Magazine() {}

	public Magazine(String name, Integer number, Date release, Publisher publisher, Collection<Article> articles) {
		this.name = name;
		this.number = number;
		this.release = release;
		this.publisher = publisher;
		this.articles = articles;
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

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Date getRelease() {
		return release;
	}

	public void setRelease(Date release) {
		this.release = release;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Collection<Article> getArticles() {
		return articles;
	}

	public void setArticles(Collection<Article> articles) {
		this.articles = articles;
	}
}
