package com.revature.pokemondb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="rate_poke_comm")
public class RatePokeComm {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int comment_id;
	@Column(name="user_id")
	private int author;
	@Column(name="is_liked")
	private Boolean isLiked;
}
