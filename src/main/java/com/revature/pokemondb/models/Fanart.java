package com.revature.pokemondb.models;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
@Table(name = "pokemon_fanart", schema = "pokemon_db")
public class Fanart {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name = "pokemon_id")
	private Pokemon pokemon;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User author;
	private String title;
	private String tags;
	@Column(name = "image")
	private String url;
	private Integer likes;
	private Integer reports;
	@Column(name = "is_flagged")
	private Boolean isFlagged;
	@Column(name="uploaded_at")
	private Date postDate;
	
	/*Constructors*/
	
	public Fanart() {
		this.id = 0;
		this.pokemon = null;
		this.author = null;
		this.url = "";
		this.likes = 0;
		this.reports = 0;
		this.isFlagged = false;
		this.postDate = Date.valueOf(LocalDate.now());
	}
	
	public Fanart(int id, Pokemon pokemon, User author, String title, String tags, String url, int likes, int reports,
			Boolean isFlagged, Date postDate, List<ArtComment> comments) {
		super();
		this.id = id;
		this.pokemon = pokemon;
		this.author = author;
		this.title = title;
		this.tags = tags;
		this.url = url;
		this.likes = likes;
		this.reports = reports;
		this.isFlagged = isFlagged;
		this.postDate = postDate;
	}
	
	/*Overrides*/
	
	@Override
	public int hashCode() {
		return Objects.hash(author, id, isFlagged, likes, pokemon, postDate, reports,
				tags, title, url);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fanart other = (Fanart) obj;
		return author == other.author && id == other.id	&& Objects.equals(isFlagged, other.isFlagged)
				&& Objects.equals(likes, other.likes) && pokemon == other.pokemon
				&& Objects.equals(postDate, other.postDate)&& Objects.equals(reports, other.reports) 
				&& Objects.equals(tags, other.tags)	&& Objects.equals(title, other.title) 
				&& Objects.equals(url, other.url);
	}

	@Override
	public String toString() {
		return "Fanart [id=" + id + ", pokemon=" + pokemon + ", author=" + author + ", title=" + title + ", tags="
				+ tags + ", url=" + url + ", likes=" + likes + ", reports=" + reports + ", isFlagged=" + isFlagged
				+ ", postDate=" + postDate + "]";
	}

	
	/*Getters and Setters*/
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pokemon getPokemon() {
		return pokemon;
	}
	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public int getReports() {
		return reports;
	}
	public void setReports(int reports) {
		this.reports = reports;
	}
	public Boolean getIsFlagged() {
		return isFlagged;
	}
	public void setIsFlagged(Boolean isFlagged) {
		this.isFlagged = isFlagged;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}
