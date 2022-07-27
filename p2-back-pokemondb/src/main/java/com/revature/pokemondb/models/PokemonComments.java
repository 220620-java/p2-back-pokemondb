package com.revature.pokemondb.models;

import java.time.Instant;
import java.util.Objects;

/*
 * Author: Dylan Cooley
 */
public class PokemonComments {
	
	private Integer id;
	private Integer pokemon_id;
	private Integer user_id;
	private String comment_content;
	private Boolean is_flagged;
	private Integer likes;
	private Instant posted_at;
	
	
	public PokemonComments() {
		this.id = 0;
		this.pokemon_id = 0;
		this.user_id = 0;
		this.comment_content = "";
		this.is_flagged = false;
		this.likes = 0;
		this.posted_at = Instant.now();
	}

	public PokemonComments(Integer id, Integer pokemon_id, Integer user_id, String comment_content, Boolean is_flagged, Integer likes,
			Instant posted_at) {
		this.id = id;
		this.pokemon_id = pokemon_id;
		this.user_id = user_id;
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
	public Integer getPokemon_id() {
		return pokemon_id;
	}
	public void setPokemon_id(Integer pokemon_id) {
		this.pokemon_id = pokemon_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
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
		return Objects.hash(comment_content, id, is_flagged, likes, pokemon_id, posted_at, user_id);
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
				&& Objects.equals(pokemon_id, other.pokemon_id) && Objects.equals(posted_at, other.posted_at)
				&& Objects.equals(user_id, other.user_id);
	}

	
	@Override
	public String toString() {
		return "PokemonComments [id=" + id + ", pokemon_id=" + pokemon_id + ", user_id=" + user_id
				+ ", comment_content=" + comment_content + ", is_flagged=" + is_flagged + ", likes=" + likes
				+ ", posted_at=" + posted_at + "]";
	}
}
