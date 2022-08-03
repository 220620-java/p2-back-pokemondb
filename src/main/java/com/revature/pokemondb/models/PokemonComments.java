package com.revature.pokemondb.models;

import com.revature.pokemondb.models.dtos.PokemonDTO;

import java.sql.Timestamp;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "pokemon_comments", schema = "pokemon_db")
public class PokemonComments {
	
	 @Id
	 @Column(name="id", updatable=false, insertable=false)
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id;
	 @ManyToOne(targetEntity = PokemonDTO.class, fetch = FetchType.LAZY)
	 @JoinColumn(name = "pokemon_id", referencedColumnName="id")
	 private PokemonDTO pokemon;
	 @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	 @JoinColumn(name = "user_id", referencedColumnName="id")
	 private User user;
	 private String comment_content;
	 @Column(name = "is_flagged")
	 private Boolean isflagged;
	 private Integer likes;
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Timestamp posted_at = new Timestamp(System.currentTimeMillis());

	@Id
	@Column(name = "id", updatable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(targetEntity = Pokemon.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "pokemon_id", referencedColumnName = "id")
	private Pokemon pokemon;
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
	private String comment_content;
	@Column(name = "is_flagged")
	private Boolean isflagged;
	private Integer likes;
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Timestamp posted_at = new Timestamp(System.currentTimeMillis());

	public PokemonComments() {

	}
	public PokemonComments(Long id, PokemonDTO pokemon, User user_id, String comment_content, Boolean is_flagged, Integer likes
			) {
		this.id = id;
		this.pokemon = pokemon;
		this.user = user_id;
		this.comment_content = comment_content;
		this.isflagged = is_flagged;
		this.likes = likes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getPokemon_id() {
		return pokemon.getId();
	}
	public void setPokemon_id(PokemonDTO pokemon) {
		this.pokemon = pokemon;
	}

	public Long getUser_id() {
		return user.getUserId();
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
		return isflagged;
	}

	public void setIs_flagged(Boolean is_flagged) {
		this.isflagged = is_flagged;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Timestamp getPosted_at() {
		return posted_at;
	}

	public void setPosted_at(Timestamp posted_at) {
		this.posted_at = posted_at;
	}

	@Override
	public int hashCode() {
		return Objects.hash(comment_content, id, isflagged, likes, pokemon, posted_at, user);
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
				&& Objects.equals(isflagged, other.isflagged) && Objects.equals(likes, other.likes)
				&& Objects.equals(pokemon, other.pokemon) && Objects.equals(posted_at, other.posted_at)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "PokemonComments [id=" + id + ", pokemon=" + pokemon + ", user=" + user.getUsername()
				+ ", comment_content=" + comment_content + ", is_flagged=" + isflagged + ", likes=" + likes
				+ ", posted_at=" + posted_at + "]";
	}
}
