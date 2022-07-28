package com.revature.pokemondb.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Barry Norton
 *
 */
@Entity
@Table(name = "pokemon_fanart")
public class Fanart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@ManyToOne
	@JoinColumn(name = "id")
	@Column(name = "user_id")
	private int author;
	@Column(name = "image")
	private String url;
	//private String title;
	@Column(name="uploaded_at")
	private Date postDate;
	@ManyToMany
	@JoinTable(name="fanart_comments",
		joinColumns = @JoinColumn(name="fanart_id"),
		inverseJoinColumns = @JoinColumn(name="user_id"))
	private List<ArtComment> comments;
	
	public Fanart() {
		this.id = 0;
		this.author = 0;
		this.url = "";
		this.postDate = Date.valueOf(LocalDate.now());
		this.comments = new ArrayList<ArtComment>();
	}
	
	public Fanart(long id, int author, String url, Date postDate, List<ArtComment> comments) {
		super();
		this.id = id;
		this.author = author;
		this.url = url;
		this.postDate = postDate;
		this.comments = comments;
	}
	/*Getters and Setters*/
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getAuthor() {
		return author;
	}
	public void setAuthor(int author) {
		this.author = author;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public List<ArtComment> getComments() {
		return comments;
	}
	public void setComments(List<ArtComment> comments) {
		this.comments = comments;
	}
}
