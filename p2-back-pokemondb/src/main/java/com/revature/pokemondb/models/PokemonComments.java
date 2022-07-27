package com.revature.pokemondb.models;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
public class PokemonComments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "id")
	private Pokemon pokemon;
	@ManyToOne
	@JoinColumn(name = "id")
	private User user;
	private String comment_content;
	private Boolean is_flagged;
	private Integer likes;
	private Instant posted_at;
	
	
	public PokemonComments() {
		this.id = 0;
		this.pokemon = new Pokemon();
		this.user = new User();
		this.comment_content = "";
		this.is_flagged = false;
		this.likes = 0;
		this.posted_at = Instant.now();
	}

	public PokemonComments(Integer id, Pokemon pokemon, User user_id, String comment_content, Boolean is_flagged, Integer likes,
			Instant posted_at) {
		this.id = id;
		this.pokemon = pokemon;
		this.user = user_id;
		this.comment_content = comment_content;
		this.is_flagged = is_flagged;
		this.likes = likes;
		this.posted_at = posted_at;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Pokemon getPokemon_id() {
		return pokemon;
	}
	public void setPokemon_id(Pokemon pokemon) {
		this.pokemon = pokemon;
	}
	public User getUser_id() {
		return user;
	}
	public void setUser_id(User user) {
		this.user = user;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public Boolean isIs_flagged() {
		return is_flagged;
	}
	public void setIs_flagged(Boolean is_flagged) {
		this.is_flagged = is_flagged;
	}
	public Integer getLikes() {
		return likes;
	}
	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public Instant getPosted_at() {
		return posted_at;
	}
	public void setPosted_at(Instant posted_at) {
		this.posted_at = posted_at;
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(comment_content, id, is_flagged, likes, pokemon, posted_at, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PokemonComments other = (PokemonComments) obj;
		return Objects.equals(comment_content, other.comment_content) && Objects.equals(id, other.id)
				&& Objects.equals(is_flagged, other.is_flagged) && Objects.equals(likes, other.likes)
				&& Objects.equals(pokemon, other.pokemon) && Objects.equals(posted_at, other.posted_at)
				&& Objects.equals(user, other.user);
	}

	
	@Override
	public String toString() {
		return "PokemonComments [id=" + id + ", pokemon=" + pokemon.getName() + ", user=" + user.getUsername()
				+ ", comment_content=" + comment_content + ", is_flagged=" + is_flagged + ", likes=" + likes
				+ ", posted_at=" + posted_at + "]";
	}
}