package com.revature.pokemondb.models;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="pokemon_comments", schema = "pokemon_db")
public class PokemonComments {
	
	 @Id
	 @Column(name="id", updatable=false, insertable=false)
	 @GeneratedValue(strategy=GenerationType.AUTO, generator="CUST_SEQ")
	 private Long id;
	 @ManyToOne
	 @JoinColumn(name = "pokemon_id", referencedColumnName="id")
	 private Pokemon pokemon;
	 @ManyToOne
	 @JoinColumn(name = "user_id", referencedColumnName="id")
	 private user;
	 private String comment_content;
	 private Boolean is_flagged;
	 private Integer likes;
	 private Instant posted_at;


	public PokemonComments() {

	}
	public PokemonComments(Long id, Pokemon pokemon, User user_id, String comment_content, Boolean is_flagged, Integer likes,
			Instant posted_at) {
		this.id = id;
		this.pokemon = pokemon;
		this.user = user_id;
		this.comment_content = comment_content;
		this.is_flagged = is_flagged;
		this.likes = likes;
		this.posted_at = posted_at;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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