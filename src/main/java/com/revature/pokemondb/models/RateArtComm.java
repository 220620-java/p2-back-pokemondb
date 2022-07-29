package com.revature.pokemondb.models;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * @author Barry Norton
 *
 */
@Entity
@Table(name="rate_art_comm", schema = "pokemon_db")
public class RateArtComm {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="comment_id")
	private ArtComment commentId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User author;
	@Column(name="is_liked")
	private Boolean isLiked;
	
	/*Constructors*/
	
	public RateArtComm() {
		super();
		this.id = 0;
		this.commentId = null;
		this.author = null;
		this.isLiked = false;
	}
	
	public RateArtComm(int id, ArtComment commentId, User author, Boolean isLiked) {
		super();
		this.id = id;
		this.commentId = commentId;
		this.author = author;
		this.isLiked = isLiked;
	}
	
	/*Overrides*/
	
	@Override
	public String toString() {
		return "RateArtComm [id=" + id + ", commentId=" + commentId + ", author=" + author + ", isLiked=" + isLiked
				+ "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(author, commentId, id, isLiked);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RateArtComm other = (RateArtComm) obj;
		return Objects.equals(author, other.author) && Objects.equals(commentId, other.commentId) && id == other.id
				&& Objects.equals(isLiked, other.isLiked);
	}
	
	/*Getters and Setters*/
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ArtComment getCommentId() {
		return commentId;
	}
	public void setCommentId(ArtComment commentId) {
		this.commentId = commentId;
	}
	public User getAuthor() {
		return author;
	}
	public void setAuthor(User author) {
		this.author = author;
	}
	public Boolean getIsLiked() {
		return isLiked;
	}
	public void setIsLiked(Boolean isLiked) {
		this.isLiked = isLiked;
	}
}
