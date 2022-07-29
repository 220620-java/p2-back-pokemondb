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
@Table(name="rate_poke_comm", schema = "pokemon_db")
public class RatePokeComm {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="comment_id")
	private PokemonComments commentId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User author;
	@Column(name="is_liked")
	private Boolean isLiked;
	
	/*Constructors*/
	
	public RatePokeComm() {
		super();
		this.id = 0;
		this.commentId = null;
		this.author = null;
		this.isLiked = false;
	}

	public RatePokeComm(int id, PokemonComments commentId, User author, Boolean isLiked) {
		super();
		this.id = id;
		this.commentId = commentId;
		this.author = author;
		this.isLiked = isLiked;
	}

	/*Overrides*/
	
	@Override
	public int hashCode() {
		return Objects.hash(author, commentId, id, isLiked);
	}

	@Override
	public String toString() {
		return "RatePokeComm [id=" + id + ", commentId=" + commentId + ", author=" + author + ", isLiked=" + isLiked
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RatePokeComm other = (RatePokeComm) obj;
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

	public PokemonComments getCommentId() {
		return commentId;
	}

	public void setCommentId(PokemonComments commentId) {
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
